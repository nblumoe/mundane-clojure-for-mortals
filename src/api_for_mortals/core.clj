(ns api-for-mortals.core)

(defn hello-handler [request]
  {:status 200
   :headers {"Content-Type" "text/plain"}
   :body (str "Hello " (:body request))})

(require '[ring.middleware.json :as json])
(def hello-json-handler
  (json/wrap-json-response
    (fn [req] {:status 200 :body (dissoc req :body)})))
