(ns tradeshift-task.main
  (:require
    [clojure.string :refer [blank? upper-case]]
    [goog.string.format]
    [goog.string :refer [format isNumeric]]
    [reagent.core :as reagent]
    [tradeshift-task.triangle :refer [triangle-type]]))


(defonce state-atom (reagent/atom {:a nil, :b nil, :c nil, :type nil}))


(defn- can-check-type?
  [state]
  (every? goog/isNumber (-> state (select-keys [:a :b :c]) vals)))


(defn check-triangle-type!
  [e]
  (let [{:keys [a b c]} @state-atom]
    (when (can-check-type? @state-atom)
      (swap! state-atom assoc :type (triangle-type a b c)))))


(defn- force-number
  [value]
  (when (and (not (blank? value)) (isNumeric value))
    (js/parseFloat value)))


(defn- update-value!
  [field-id e]
  (let [value (-> e .-target .-value force-number)]
    (swap! state-atom assoc field-id value)
    (if (nil? value)
      (swap! state-atom assoc :type nil)
      (check-triangle-type! e))))


(defn- layout
  [state]
  [:main {:data-ts "Main"}
   [:div {:data-ts "MainContent"}
    [:h1 "Triangle Type"]
    [:p "Please provides all triangle sides to check its type."]
    [:form {:data-ts "Form"}
     [:fieldset
      (for [label [:a :b :c]]
        ^{:key label}
        [:label
         [:span (-> label name upper-case)]
         [:input {:type "number"
                  :required true
                  :id (format "input-" (name label))
                  :on-change (partial update-value! label)}]])
      (when (-> state :type some?)
        [:dl {:class "ts-info"} [:dt (format "Type: %s" (name (:type state)))]])]]]])


(defn- main
  []
  [layout @state-atom])


(defn ^:export init
  []
  (when-let [app-element (.getElementById js/document "app")]
    (reagent/render [main] app-element)))
