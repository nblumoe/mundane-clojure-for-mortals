(ns api-for-mortals.auth
  (:require [buddy.sign.jws :as jws]
            [buddy.auth :refer [authenticated?]]
            [buddy.auth.middleware :refer [wrap-authentication]]
            [buddy.auth.backends.token :refer [jws-backend]]
            [ring.util.http-response :refer [ok unauthorized]]
            [compojure.api.sweet :refer :all]))

(def secret "abc123")

(def user {:id 3
           :username "foo"
           :password "bar"})

(def users {(:id user) user})

(defn login [username password]
  (if (and (= username (:username user))
        (= password (:password user)))
    {:token (jws/sign {:id (:id user)} secret)}
    nil))

(def auth-backend (jws-backend {:secret secret}))
(defn auth-mw [handler]
  (fn [request]
    (if (authenticated? request)
      (handler request)
      (unauthorized {:error "Invalid Token"}))))
(defroutes* auth-api
  (context "/auth-api" []

    (wrap-authentication
      (GET* "/users/:id" {:as request}
        :middlewares [api-for-mortals.auth/auth-mw]
        :header-params [authorization :- String]
        :path-params [id :- Long]
        (if (= (:id (:identity request)) id)
          (ok (users id))
          (ok {:error "not auth"})))
      auth-backend)

    (POST* "/login" []
      :body-params [username :- String
                    password :- String]
      (ok (login username password)))))
