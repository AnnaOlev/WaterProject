package com.example.waterBalance

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class NotificationService : Service() {
    companion object {
        // Необходимо для гарантии, что будет запущен только один экземпляр сервиса.
        var isRunning = false
            private set
    }
    private val scheduler = Executors.newScheduledThreadPool(1)

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        addChecks()
        isRunning = true
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        isRunning = false
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    // В этой функции происходит добавление задач в планировщик
    private fun addChecks(){
        // Пример реализации напоминаний о необходимости выпить воды.
        // Функция вызывается каждые 15 минут, сверяет разницу между текущим временем и временем
        // добавления последней записи. Если прошло более двух часов(например), то отправляется уведомление.
        scheduler.scheduleAtFixedRate({
            // Максимальное колличество часов без воды
            val maxDelta = 2

            // Примерный запрос к будущей базе данных.
            // В самой базе, для каждой записи необходимо наличие поля с отметкой времени.
            val query = "SELECT timestamp FROM stats ORDER BY timestamp FETCH FIRST ROW ONLY"

            val lastDrinkTime = Calendar.getInstance().time // Здесь будет результат запроса
            val currentTime = Calendar.getInstance().time

            var delta = currentTime.time - lastDrinkTime.time
            delta = TimeUnit.HOURS.convert(delta, TimeUnit.MILLISECONDS) + 2

            if(delta >= maxDelta){
                val notification = NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.notification_icon_background)
                        .setContentTitle("Water Balance")
                        .setContentText("Необходимо восполнить запас жидкости")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)

                with(NotificationManagerCompat.from(this)){
                    notify(0,notification.build())
                }
            }
        },0,15, TimeUnit.MINUTES)
    }
}
