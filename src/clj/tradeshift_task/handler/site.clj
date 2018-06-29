(ns tradeshift-task.handler.site
  (:require
    [integrant.core :as ig]
    [ring.util.response :as response]
    [tradeshift-task.handler.index :as index]))


(defn site-routes
  [conf request]
  (response/content-type
    (let [handler (case (:bidi-route request)
                    :site/index index/render
                    (fn [_ _] (response/not-found nil)))]
      (handler conf request))
    "text/html"))


(defmethod ig/init-key :tradeshift-task.handler/site
  [_ conf]
  (partial site-routes conf))
