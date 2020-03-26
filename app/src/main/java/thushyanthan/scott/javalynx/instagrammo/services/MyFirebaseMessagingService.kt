package thushyanthan.scott.javalynx.instagrammo.services

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService: FirebaseMessagingService(){
    private var deviceToken: String=""
    override fun onNewToken(token: String) {
        this.deviceToken = token
        sendRegistrationToServer(token)
    }

    private fun sendRegistrationToServer(token: String?) {
        // TODO: Implement this method to send token to your app server.
    }

    override fun onMessageReceived(p0: RemoteMessage) {

    }
}