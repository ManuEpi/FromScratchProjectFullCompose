package com.manuepi.fromscratchprojectv2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.manuepi.fromscratchprojectv2.navigation.MainNavigation
import com.manuepi.fromscratchprojectv2.ui.theme.FromScratchProjectV2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FromScratchProjectV2Theme {
                MainNavigation()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FromScratchProjectV2Theme {
        MainNavigation()
    }
}