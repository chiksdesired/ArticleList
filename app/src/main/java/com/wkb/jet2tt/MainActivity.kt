package com.wkb.jet2tt

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wkb.jet2tt.adapter.ArticlesListAdapter
import com.wkb.jet2tt.model.ArticalModel
import com.wkb.jet2tt.network.ApiInterface
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback


class MainActivity : AppCompatActivity() {
    lateinit var pDialog: ProgressDialog
    var page = 1
    var limit = 10
    var articleListAdapter: ArticlesListAdapter? = null
    var articleList: List<ArticalModel> =  ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /* Display progress dialog*/
        displayProgressDialog()


        /* Network call fetch Article List*/
        callGetArticle()


    }

    fun displayProgressDialog() {

        pDialog = ProgressDialog(this@MainActivity)
        pDialog!!.setMessage("Loading..")
        pDialog!!.setCancelable(false)
        pDialog!!.isIndeterminate = false
        pDialog!!.show()
    }

    fun dismissProgressDialog() {
        if (pDialog.isShowing) {
            pDialog.dismiss()
        }
    }

    private fun callGetArticle() {
        val apiService = ApiInterface.create()
        val call = apiService.getArtical(page, limit)
        call.enqueue(object : Callback<List<ArticalModel>> {
            override fun onResponse(
                call: Call<List<ArticalModel>>,
                response: retrofit2.Response<List<ArticalModel>>?
            ) {
                dismissProgressDialog()
                if(response!!.isSuccessful)
                {
                    articleList = response.body()!!
                    articleListAdapter = ArticlesListAdapter(this@MainActivity!!)
                    articleListAdapter?.setData(articleList as ArrayList<ArticalModel>)
                    rvArticleList.layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, true)
                    rvArticleList.adapter = articleListAdapter



                }


            }

            override fun onFailure(call: Call<List<ArticalModel>>, t: Throwable) {
                dismissProgressDialog()


            }
        })


    }


}
