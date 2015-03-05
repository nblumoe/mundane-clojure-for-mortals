(ns api-for-mortals.server
  (:require [compojure.api.sweet :refer :all]
            [api-for-mortals.compojure :as comp]
            [api-for-mortals.swagger :as swagger]
            [api-for-mortals.auth :as auth]))

(defapi api-app
  (swagger-ui)
  (swagger-docs)
  (swaggered "No Authentication" swagger/api)
  (swaggered "Authentication" auth/auth-api)
  comp/api)
