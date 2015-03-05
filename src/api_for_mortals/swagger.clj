(ns api-for-mortals.swagger
  (:require [ring.util.http-response :refer [ok]]
            [compojure.api.sweet :refer :all]))

(defroutes* api
  (context "/api" []
    (GET* "/jolts/:id" [id]
      (ok {:id id}))
    (POST* "/jolts" []
      :body-params [body :- Long]
      (ok {:data body}))))
