(ns ifes.tsp.brute_force
  (:require
    [ifes.tsp.tsp-core :as tsp-core]
    [clojure.math.combinatorics :as combo]
    )
  (:gen-class)
)

(def gerar-rotas (combo/permutations (range 1 (inc tsp-core/num-cidades))))

(defn calcular-rotas
  [mapa rotas]
  (map
    (fn [rota] {:rota rota :custo (tsp-core/calcula-rota mapa rota)})
    rotas
  )
)

(defn rotas-validas
  [rotas]
  (filter (fn [rota] (> (get rota :custo) -1)) rotas)
)

(defn melhor-rota
  [mapa rotas]
  (apply min-key :custo (rotas-validas (calcular-rotas mapa rotas)))
)