(ns api-for-mortals.compojure
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [clojure.edn :as edn]
            [ring.middleware.json :as json]
            [ring.middleware.params :as params]))

(defn rnd-int-in-range [min max]
  (+ min (rand-int (+ (- max min) 1))))

(defn randomizer [min-str max-str]
  (let [min (edn/read-string min-str)
        max (edn/read-string max-str)]
    (rnd-int-in-range min max)))

(defroutes api-routes
  (GET "/bounce-request" request {:body (dissoc request :body)})
  (GET "/users/:id" [id] {:body {:name "foo" :id id :role "admin"}})
  (GET "/randomizer" [min max] {:body {"randomNumber" (randomizer min max)}})
  (route/not-found {:body {:error "Page not found"}}))

(def api
  (-> api-routes
    params/wrap-params
    json/wrap-json-response))
