package com.example.compose_animations.sample_anim

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.expandIn
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose_animations.R
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState

@Composable
fun JerryAnimVisibility(visible: MutableState<Boolean>) {
  val density = LocalDensity.current

  AnimatedVisibility(
      visible = visible.value,
      enter = slideInVertically {
        with(density) {
          -80.dp.roundToPx()
        }
      } + expandVertically(
          expandFrom = Alignment.Top
      ) + fadeIn(initialAlpha = 0.3f),
      exit = slideOutVertically() + shrinkVertically() + fadeOut()
  ) {
    Text(
        text = "Hello Compose Animations", modifier = Modifier
        .wrapContentSize()
        .background(color = Color.Blue),
        style = TextStyle(color = Color.White, fontSize = 48.sp, textAlign = TextAlign.Center)
    )
  }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun JerryScaleAnimation(visible: MutableState<Boolean>) {
  AnimatedVisibility(
      visible = visible.value,
      enter = scaleIn(initialScale = 0f),
      exit = scaleOut()
  ) {
    Image(painterResource(id = R.drawable.jerry), contentDescription = null)
  }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun AnimationHorizontalPager() {
  val pagerState = rememberPagerState()
  val visible = remember {
    mutableStateOf(false)
  }
  Column(
      modifier = Modifier
          .fillMaxSize(),
      verticalArrangement = Arrangement.Top
  ) {

    HorizontalPager(
        count = 10, state = pagerState,
        modifier = Modifier
            .weight(1f)
            .fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) { pageIndex ->
      when (pageIndex) {
        0 -> {
          Column(
              modifier = Modifier
                  .weight(1f)
                  .fillMaxWidth()
          ) {
            JerryAnimVisibility(visible)
          }
        }
        1 -> {
          Column(
              modifier = Modifier
                  .weight(1f)
                  .fillMaxSize()
          ) {
            JerryScaleAnimation(visible)
          }
        }
        2 -> {
          JerryExpandAnimation(visible = visible)
        }
        3 -> {
          JerryExpandHorizontallyAnimation(visible = visible)
        }
        4 -> {
          JerryExpandVerticallyAnimation(visible = visible)
        }
        5 -> {
        }
        6 -> {
        }
        7 -> {
        }
        8 -> {
        }
      }
      visible.value = false
    }

    PlayAnimButton(visible = visible)

    HorizontalPagerIndicator(
        pagerState = pagerState,
        modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .padding(16.dp),
    )
  }
}

@Composable
fun JerryExpandAnimation(visible: MutableState<Boolean>) {
  AnimatedVisibility(
      visible = visible.value,
      enter = expandIn(expandFrom = Alignment.BottomEnd) + fadeIn(initialAlpha = 0.2f),
      exit = shrinkOut() + fadeOut()
  ) {
    Image(painterResource(id = R.drawable.jerry), contentDescription = null)
  }
}

@Composable
fun JerryExpandHorizontallyAnimation(visible: MutableState<Boolean>) {
  AnimatedVisibility(
      visible = visible.value,
      enter = expandHorizontally(expandFrom = Alignment.End) + fadeIn(initialAlpha = 0.2f),
      exit = shrinkOut() + fadeOut()
  ) {
    Image(painterResource(id = R.drawable.jerry), contentDescription = null)
  }
}

@Composable
fun JerryExpandVerticallyAnimation(visible: MutableState<Boolean>) {
  AnimatedVisibility(
      visible = visible.value,
      enter = expandVertically(expandFrom = Alignment.Top) + fadeIn(initialAlpha = 0.2f),
      exit = shrinkOut() + fadeOut()
  ) {
    Image(painterResource(id = R.drawable.jerry), contentDescription = null)
  }
}

@Composable
private fun PlayAnimButton(
  visible: MutableState<Boolean>,
) {
  Row(
      modifier = Modifier.fillMaxWidth(),
      verticalAlignment = Alignment.Top,
      horizontalArrangement = Arrangement.Center
  ) {
    Button(onClick = { visible.value = !visible.value }) {
      Text(text = "Play Animation")
    }
  }
}
