package no.usn.ruud.testprosjekt2.utils

//
//open class FillDatabaseWithContentWorker(appContext: Context, workerParams: WorkerParameters) :
//    CoroutineWorker(appContext, workerParams) {
//    private val dbDao = WorkoutDatabase.getInstance(applicationContext).workoutDatabaseDao()
//    override suspend fun doWork(): Result {
//        try {
//            //Legg til eksempler på ord
//            Log.i("FillDatabaseWithContentWorker", "Henter database")
//
////                if(dbDao.getAll().value?.isEmpty() == false)
////                    dbDao.deleteAll()
//            dbDao.insertWorkout(Workout(1, date = 23))
//            Log.i("FillDatabaseWithContentWorker", "Før innfylling løkken")
//            dbDao.insertExercise(
//                Exercise(
//                    exerciseID = 0,
//                    //Alle treninger får referanse til økt 1
//                    name = "Bench",
//                    reps = 5,
//                    sets = 5
//                )
//            )
//            Log.i(TAG, "FillDatabaseWithContentWorker SUCESS")
//            return Result.success()
//        } catch (ex: Exception) {
//            Log.e(TAG, "FillDatabaseWithContentWorker FAILED")
//            return Result.failure()
//        }
//
//        // Indicate whether the work finished successfully with the Result
//
//    }
//
//    companion object {
//        private const val TAG = "Database, FillDatabase"
//    }
//}