(ns tradeshift-task.triangle)


(defn triangle-type
  "Takes a triangle as three lengths sides and return its type:
   - :non-triangle
   - :equilateral
   - :isosceles
   - :scalene"
  [a b c]
  (if (or
        (not (every? pos? [a b c]))
        (not (and (< a (+ b c))
                  (< b (+ a c))
                  (< c (+ a b)))))
    :non-triangle
    (let [distinct-sides (set [a b c])]
      (case (count distinct-sides)
        1 :equilateral
        2 :isosceles
        :scalene))))
