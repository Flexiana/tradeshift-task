(ns tradeshift-task.handler.index
  (:require
    [hiccup.core :as hiccup]))


(defn- layout
  [request]
  [:html
   [:head
    [:title "Tradeshift Task"]
    [:link {:rel "stylesheet", :href "//d5wfroyti11sa.cloudfront.net/prod/client/ts-10.0.25.min.css"}]
    [:meta {:name "viewport", :content "width=device-width"}]]
   [:body
    [:div#app]
    [:script {:src "//d5wfroyti11sa.cloudfront.net/prod/client/ts-10.0.25.min.js"}]
    [:script {:src "/js/main.js"}]
    [:script "tradeshift_task.main.init();"]]])


(defn render
  [conf request]
  {:body (hiccup/html (layout request)), :status 200})
