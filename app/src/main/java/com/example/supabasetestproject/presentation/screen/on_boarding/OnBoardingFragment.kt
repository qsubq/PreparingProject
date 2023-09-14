package com.example.supabasetestproject.presentation.screen.on_boarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.supabasetestproject.R
import java.util.LinkedList
import java.util.Queue

class OnBoardingFragment : Fragment() {

    companion object {
        val onBoardingQueue =
            LinkedList(
                mutableListOf(
                    OnBoardingModel(
                        R.drawable.on_boarding_image_1,
                        "Real-time Tracking First",
                        "Enjoy quick pick-up and delivery to your destination",
                    ),
                    OnBoardingModel(
                        R.drawable.on_boarding_image_2,
                        "Real-time Tracking Second",
                        "Different modes of payment either before and after delivery without stress",
                    ),
                    OnBoardingModel(
                        R.drawable.on_boarding_image_3,
                        "Real-time Tracking Third",
                        "Enjoy quick pick-up and delivery to your destination",
                    ),
                ),
            )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                OnBoardingScreen(navController = findNavController(), onBoardingQueue)
            }
        }
    }
}

@Composable
fun OnBoardingScreen(navController: NavController, onBoardingQueue: Queue<OnBoardingModel>) {
    val currentItem = remember { mutableStateOf(onBoardingQueue.poll()) }

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
        Image(
            modifier = Modifier.padding(top = 50.dp),
            imageVector = ImageVector.vectorResource(id = currentItem.value!!.image),
            contentDescription = null,
        )
        Text(
            modifier = Modifier.padding(top = 60.dp),
            text = currentItem.value!!.text,
            fontSize = 24.sp,
            color = Color(0xFF0560FA),
            fontWeight = FontWeight.Bold,
        )
        Text(
            modifier = Modifier.padding(top = 12.dp),
            text = currentItem.value!!.text,
            fontSize = 16.sp,
            color = Color(0xFF3A3A3A),
            fontWeight = FontWeight.Normal,
        )

        if (onBoardingQueue.isEmpty()) {
            Column(
                modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 80.dp).height(70.dp)
                    .testTag("SignUpBtn"),
            ) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        if (onBoardingQueue.isEmpty()) {
                            navController.navigate(R.id.action_onBoardingFragment_to_signUpFragment)
                        }
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF0560FA)),
                ) {
                    Text(
                        text = "Sign Up",
                        fontSize = 16.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                    )
                }
                Spacer(modifier = Modifier.height(60.dp))
            }
        } else {
            Box(Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp, top = 80.dp)) {
                Button(
                    modifier = Modifier.align(Alignment.CenterEnd).width(56.dp).height(28.dp)
                        .testTag("NextBtn"),
                    onClick = {
                        currentItem.value = onBoardingQueue.poll()
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF0560FA)),
                ) {
                    Text(
                        text = "Next",
                        fontSize = 9.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                    )
                }

                OutlinedButton(
                    modifier = Modifier.align(Alignment.CenterStart).width(56.dp).height(28.dp)
                        .testTag("SkipBtn"),
                    onClick = {
                        navController.navigate(R.id.action_onBoardingFragment_to_signUpFragment)
                    },
                    border = BorderStroke(1.dp, color = Color(0xFF0560FA)),
                ) {
                    Text(
                        text = "Skip",
                        fontSize = 9.sp,
                        color = Color(0xFF0560FA),
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
            Spacer(modifier = Modifier.height(60.dp))
        }
    }
}
