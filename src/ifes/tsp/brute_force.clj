(ns ifes.tsp.brute_force
  (:require
    [ifes.tsp.tsp-core :as tsp-core]
    [clojure.math.combinatorics :as combo]
    )
  (:gen-class)
)


(defn gerar-rotas "Gera todas as rotas possíveis" [] (combo/permutations (range 1 (inc tsp-core/num-cidades))))

(defn calcular-rotas
  "Calcula o valor de uma rota específica"
  [mapa rotas]
  (map
    (fn [rota] {:rota rota :custo (tsp-core/calcula-rota mapa rota)})
    rotas
  )
)

(defn melhor-rota
  "Retorna a rota de menor custo"
  [mapa rotas]
  (apply min-key :custo (tsp-core/rotas-validas (calcular-rotas mapa rotas)))
)
