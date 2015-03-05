(defproject api-for-mortals "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [ring/ring-core "1.3.2"]
                 [ring/ring-jetty-adapter "1.3.2"]
                 [ring/ring-json "0.3.1"]
                 [compojure "1.3.1"]
                 [metosin/compojure-api "0.17.0"]
                 [metosin/ring-swagger-ui "2.1.1-M1"]
                 [buddy "0.4.1"]
                 [com.datomic/datomic-free "0.9.5130"]]
  :plugins [[lein-ring "0.9.1"]]
  :ring {:handler api-for-mortals.server/api-app})
