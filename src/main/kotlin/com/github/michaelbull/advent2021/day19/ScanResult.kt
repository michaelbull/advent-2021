package com.github.michaelbull.advent2021.day19

import com.github.michaelbull.advent2021.math.Vector3

data class ScanResult(
    val beacons: Set<Vector3>,
    val translations: Set<Vector3>
)
