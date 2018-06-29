(ns tradeshift-task.main
  (:gen-class)
  (:require
    [clojure.java.io :as io]
    [duct.core :as duct]))


(duct/load-hierarchy)


(defn -main
  [& args]
  (let [keys (or (duct/parse-keys args) [:duct/daemon])]
    (-> (duct/read-config (io/resource "tradeshift_task/config.edn"))
        (duct/prep keys)
        (duct/exec keys))))
