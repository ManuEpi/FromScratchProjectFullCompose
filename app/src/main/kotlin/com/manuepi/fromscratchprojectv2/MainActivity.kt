package com.manuepi.fromscratchprojectv2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.manuepi.fromscratchprojectv2.navigation.Navigator
import com.manuepi.fromscratchprojectv2.ui.theme.FromScratchProjectV2Theme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //installSplashScreen()

        setContent {
            FromScratchProjectV2Theme {
                com.manuepi.fromscratchprojectv2.navigation.MainNavigation(navigator = navigator)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FromScratchProjectV2Theme {
        com.manuepi.fromscratchprojectv2.navigation.MainNavigation(navigator = com.manuepi.fromscratchprojectv2.navigation.Navigator())
    }
}