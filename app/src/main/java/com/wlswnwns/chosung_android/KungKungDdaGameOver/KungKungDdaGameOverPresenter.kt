package com.wlswnwns.chosung_android.KungKungDdaGameOver

import com.wlswnwns.chosung_android.ChosungApplication
import com.wlswnwns.chosung_android.KungKungDdaGame.KungKungDdaGameModel
import com.wlswnwns.chosung_android.item.Game
import com.wlswnwns.chosung_android.item.User
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class KungKungDdaGameOverPresenter(view: KungKungDdaGameOverContract.View) :
    KungKungDdaGameOverContract.Presenter {


    var view: KungKungDdaGameOverContract.View
    var model: KungKungDdaGameOverModel

    init {
        this.view = view
        this.model = KungKungDdaGameOverModel()

    }


    override fun viewDidLoad() {
        view.viewInit()

        model.InitSockerListner(object : KungKungDdaGameModel.SockerListner {
            override fun onDataReceived(jsonObject: JSONObject) {
                val action = Runnable {
                    try {
                        if (jsonObject.getString("strEvent").equals("EXIT_ROOM")) {

                            if (jsonObject.getString("msg").equals("THE_ROOM_IS_DESTROYED")) {
                                view.exitRoom()
                                ChosungApplication.client?.disconnect()

                            }


                        } else if (jsonObject.getString("strEvent").equals("THE_ROOM_IS_DESTROYED")) {
                            view.exitRoom()
                            ChosungApplication.client?.disconnect()
                        }


                    } catch (e: IOException) {
                        e.printStackTrace()
                    } catch (e: JSONException) {
                        e.printStackTrace()

                    }

                }

                ChosungApplication.activity.runOnUiThread(action)


            }
        })
    }

    override fun finishGame() {
        view.exitRoom()
        ChosungApplication.client?.disconnect()




    }

}