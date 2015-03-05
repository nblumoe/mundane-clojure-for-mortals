(ns api-for-mortals.datomic
  (:require [datomic.api :as d]))
(def uri "datomic:mem://jolts")

(d/create-database uri)

(def conn (d/connect uri))
(def schema-tx [;; jolts
                {:db/id #db/id[:db.part/db]
                 :db/ident :jolt/sender
                 :db/valueType :db.type/string
                 :db/cardinality :db.cardinality/one
                 :db.install/_attribute :db.part/db}])
@(d/transact conn schema-tx)
(def seed-tx [{:db/id #db/id[:db.part/user -1000001]
               :jolt/sender "nblu@futurice.com"}])

@(d/transact conn seed-tx)
(def results (q '[find ?j :where [?j :jolt/sender]] (db conn)))
(def id (ffirst results))
(def entity (-> conn db (d/entity id)))
(:jolt/sender entity)
