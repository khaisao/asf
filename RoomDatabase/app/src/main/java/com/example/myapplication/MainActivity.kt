package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.adapter.UserListAdapter
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.model.User
import com.example.myapplication.vm.UserViewModel
import com.example.myapplication.vm.UserViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    //    val adapter = UserAdapter()
    private lateinit var adapter: UserListAdapter
    private val viewModel: UserViewModel by viewModels() {
        UserViewModelFactory(application)
    }
    private val listUser = arrayListOf<User>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = UserListAdapter()
        binding.rvUser.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvUser.adapter = adapter
        viewModel.users.observe(this, {
            adapter.submitList(it)
        })
        binding.btnAdd.setOnClickListener {
            val id = binding.edtId.text.toString()
            val account = binding.edtUser.text.toString()
            val pass = binding.edtPass.text.toString()
            viewModel.addUser(User(id.toInt(), account, pass))
        }
        binding.btnEdit.setOnClickListener {
            val id = binding.edtId.text.toString()
            val account = binding.edtUser.text.toString()
            val pass = binding.edtPass.text.toString()
            viewModel.updateUser(User(id.toInt(), account, pass))
        }
        binding.btnDelete.setOnClickListener {
            val id = binding.edtId.text.toString()
            viewModel.deleteUser(id.toInt())
        }
    }
}