package uz.otamurod.kmp.compose.ui

import androidx.compose.runtime.Composable

typealias OnAddType = (List<String>) -> Unit
typealias onDismissType = () -> Unit
typealias composeFun = @Composable () -> Unit
typealias topBarFun = @Composable (Int) -> Unit

@Composable
fun emptyComposable(){

}