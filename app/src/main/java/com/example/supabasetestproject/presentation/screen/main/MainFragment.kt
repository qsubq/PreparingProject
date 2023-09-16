package com.example.supabasetestproject.presentation.screen.main

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDeepLinkBuilder
import androidx.work.ForegroundInfo
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.supabasetestproject.R
import com.example.supabasetestproject.data.local_data_source.LocalRepositoryImpl
import com.example.supabasetestproject.data.model.UserModelResponse
import com.example.supabasetestproject.data.remote_data_source.RemoteRepositoryImpl
import com.example.supabasetestproject.databinding.FragmentMainBinding
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private val email by lazy {
        arguments?.getString("email")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userNameLiveData: MutableLiveData<List<UserModelResponse>> = MutableLiveData()

        with(binding) {
            checkBox.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
            }

            val repositoryImpl = LocalRepositoryImpl(requireContext())
            imgChina.setOnClickListener {
                repositoryImpl.setLanguage("sg")
                requireActivity().recreate()
            }
            imgRus.setOnClickListener {
                repositoryImpl.setLanguage("ru")
                requireActivity().recreate()
            }
            imgUsa.setOnClickListener {
                repositoryImpl.setLanguage("en")
                requireActivity().recreate()
            }
            tvSendNotification.setOnClickListener {
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    10,
                )
                createNotificationChannel()

                val worker = OneTimeWorkRequestBuilder<NotificationWorker>()
                    .setInitialDelay(1, TimeUnit.MINUTES)
                    .build()
                WorkManager.getInstance(requireContext()).enqueue(worker)
            }
        }
        userNameLiveData.observe(viewLifecycleOwner) {
            binding.tvUserName.text = it.first().name
        }

        lifecycleScope.launch {
            val remoteRepository = RemoteRepositoryImpl()
            userNameLiveData.value = email?.let { remoteRepository.getUserInfoForEmail(it) }
        }
    }

    class NotificationWorker(
        private val context: Context,
        workerParameters: WorkerParameters,
    ) : Worker(context, workerParameters) {
        private val notification = createNotification()

        override fun doWork(): Result {
            return try {
                if (ActivityCompat.checkSelfPermission(
                        context,
                        Manifest.permission.POST_NOTIFICATIONS,
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    NotificationManagerCompat.from(context).notify(101, notification)
                    Result.success()
                } else {
                    Result.failure()
                }
            } catch (e: Exception) {
                Result.failure()
            }
        }

        override fun getForegroundInfo(): ForegroundInfo {
            return ForegroundInfo(101, notification)
        }

        private fun createNotification(): Notification {
            val intent = NavDeepLinkBuilder(context)
                .setGraph(R.navigation.nav_graph)
                .setDestination(R.id.mainFragment)
                .createPendingIntent()

            val notification = Notification.Builder(context, "99")
                .setContentText("Notification after 3 minutes idle")
                .setContentTitle("Reminder notification")
                .setContentIntent(intent)
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.rus_flag)
                .build()
            return notification
        }
    }

    private fun createNotificationChannel() {
        val notificationManager =
            requireContext().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(
            NotificationChannel(
                "99",
                "ChannelName",
                NotificationManager.IMPORTANCE_DEFAULT,
            ),
        )
    }
}
