(ns tradeshift-task.triangle-test
  (:require
    [clojure.test :refer [are deftest]]
    [tradeshift-task.triangle :refer [triangle-type]]))


(deftest triangle-type-test
  (are [expected args] (= expected (apply triangle-type args))

       :non-triangle [-1 1 1]
       :non-triangle [0 1 1]
       :non-triangle [1 0 1]
       :non-triangle [1 1 0]
       :equilateral [1 1 1]
       :isosceles [1 2 2]
       :scalene [2 3 4]))
