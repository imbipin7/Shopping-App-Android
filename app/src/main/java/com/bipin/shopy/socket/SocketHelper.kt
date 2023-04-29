package com.bipin.shopy.socket

import android.util.Log
import com.github.nkzawa.emitter.Emitter
import com.github.nkzawa.socketio.client.Ack
import com.github.nkzawa.socketio.client.IO
import com.github.nkzawa.socketio.client.Socket

object SocketHelper {

    private val options: IO.Options by lazy { IO.Options() }
    private var socket: Socket? = null

    private val onConnect = Emitter.Listener {


        Log.e("Socket", "Connect")
        Log.d("SocketHelper", "onConnect== $it")


    }
    private val onDisconnect = Emitter.Listener {
        Log.e("Socket", "Disconnect")
        Log.d("SocketHelper", "disconnect  ${it[0]}")
//        socket = null
//        connectSocket()
    }
    private val onConnectError = Emitter.Listener {
        Log.d("SocketHelper", "connectionError  ${it[0]}")
        socket?.connect()
    }
    private val onConnectTimeout = Emitter.Listener {
        Log.d("SocketHelper", "onConnectTimeout $it")
        socket?.connect()
    }

    private val onReconnecting = Emitter.Listener {
        Log.d("SocketHelper", "onReconnecting $it")
    }

    fun getSocketHelper(): Socket = socket!!

    fun onSocket() {
        listener(Socket.EVENT_CONNECT, onConnect)
        listener(Socket.EVENT_DISCONNECT, onDisconnect)
        listener(Socket.EVENT_CONNECT_ERROR, onConnectError)
        listener(Socket.EVENT_CONNECT_TIMEOUT, onConnectTimeout)
        listener(Socket.EVENT_RECONNECTING, onReconnecting)
    }

    fun listener(key: String, emitter: Emitter.Listener) {
        if (socket?.hasListeners(key) == true)
            revokeListener(key)
        socket?.on(key, emitter)
    }

    fun isConnected(): Boolean = socket?.connected() ?: false

    fun connectSocket() {
//        options.transports = arrayOf(Polling.NAME)
        options.query = "transport=polling&user_id=user_id&connect_from=app"

        if (socket == null)
            socket = IO.socket(SocketsURL.SOCKET_STAGING_URL, options)
        Log.e("TAG", "connectSocket: ${SocketsURL.SOCKET_STAGING_URL + options.query}")

        onSocket()
        socket?.connect()
        Log.e("TAG", "connectSocket: ${socket?.connected()}")

    }

    fun revokeListener(key: String) = socket?.off(key)

    fun disconnectSocket() = socket?.close()

    fun <T> emit(key: String, data: T, ack: Ack? = null) {
        Log.e("Sent", "=====$key ==== $data")
        socket?.emit(key, data, ack)
    }
}