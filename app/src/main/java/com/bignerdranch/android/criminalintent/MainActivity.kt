package com.bignerdranch.android.criminalintent

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)

        if(currentFragment == null){
            val fragment = CrimeListFragment.newInstance()
            supportFragmentManager.beginTransaction().add(R.id.fragment_container, fragment).commit()
            // 프래그먼트 매니저에 접근해서 트랜잭션을 생성하고 생성된 트랜잭션으로 프래그먼트를 추가
            // 프래그먼트 트랜잭션은 프래그먼트를 추가 삭제 변경 첨부 등을 하는 데 사용 됨.
            // 즉 프래그먼트를 다룰 땐 트랜잭션이 꼭 필요
        }

    }
}