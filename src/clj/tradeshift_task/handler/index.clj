(ns tradeshift-task.handler.index
  (:require
    [hiccup.core :as hiccup]))


(defn- layout
  [request]
  [:html
   [:head [:title "Tradeshift Task"]]
   [:body
    [:h1 "Hallo"]]])


(defn render
  [conf request]
  {:body (hiccup/html (layout request)), :status 200})
