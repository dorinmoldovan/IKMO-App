package com.ikmo_app.algorithm;

import com.ikmo_app.model.Kangaroo;
import com.ikmo_app.model.Mob;
import com.ikmo_app.objective_functions.OF;
import org.apache.commons.lang3.SerializationUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class IKMO {

    private Integer N;
    private Integer I;
    private Integer D;
    private Integer M;
    private Integer K;
    private Integer sMin;
    private Integer sMax;
    private Integer CT;
    private Double eps;
    private OF of;
    private Random rand;

    public IKMO(Integer n, Integer i, Integer d, Integer m, Integer k, Integer sMin, Integer sMax, Integer CT, Double eps, OF of) {
        this.N = n;
        this.I = i;
        this.D = d;
        this.M = m;
        this.K = k;
        this.sMin = sMin;
        this.sMax = sMax;
        this.CT = CT;
        this.eps = eps;
        this.of = of;
        this.rand = new Random(System.currentTimeMillis());
    }

    public List<Kangaroo> generatePopulation() {
        List<Kangaroo> population = new ArrayList<Kangaroo>();
        for(int i = 0; i < N; i++) {
            Kangaroo kangaroo = new Kangaroo(rand, this.D, of.getMinValue(), of.getMaxValue(), this.sMin, this.sMax, this.CT, this.eps);
            population.add(kangaroo);
        }
        return population;
    }

    public Result run() {
        Result result = new Result();

        double startTime = System.currentTimeMillis();
        List<Kangaroo> population = generatePopulation();
        Kangaroo gBest = null;

        for(int i = 0; i < population.size(); i++) {
            double fitness = of.compute(population.get(i).getPosition());
            population.get(i).setFitness(fitness);
            population.get(i).setLocalBestFitness(fitness);
            if(i == 0) {
                gBest =  (Kangaroo) SerializationUtils.clone(population.get(i));
            } else {
                if(fitness < gBest.getFitness()) {
                    gBest =  (Kangaroo) SerializationUtils.clone(population.get(i));
                }
            }
        }

        List<Mob> mobs = null;

        for(int i = 0; i < I; i++) {
            if(i % K == 0) {
                mobs = new ArrayList<>();
                for(int j = 0; j < M; j++) {
                    mobs.add(new Mob());
                }
                for(int j = 0; j < population.size(); j++) {
                    Kangaroo kangaroo = population.get(j);
                    int mobIndex = rand.nextInt(M);
                    Mob mob = mobs.get(mobIndex);
                    mob.getKangaroos().add(kangaroo);
                    List<Double> velocity = new ArrayList<>();
                    for(int k = 0; k < this.D; k++) {
                        int vVal = rand.nextInt(3);
                        if(vVal == 0) {
                            velocity.add(1.0);
                        } else if(vVal == 1) {
                            velocity.add(1.5);
                        } else {
                            velocity.add(2.0);
                        }
                    }
                    kangaroo.setVelocity(velocity);
                }
                for(int j = 0; j < M; j++) {
                    mobs.get(j).sortKangaroos();
                    mobs.get(j).distributeKangaroos();
                    mobs.get(j).kangaroosSniffing();
                    mobs.get(j).maleKangaroosSniffing();
                }
            }

            for(int j = 0; j < mobs.size(); j++) {
                Mob mob = mobs.get(j);

                // mob leader

                if(mob.getMobLeader() != null) {
                    if(mob.getMobLeader().getConfrontationFlag() == true) {
                        mob.getMobLeader().computeNewPosition(true, eps, null);
                    } else {
                        Kangaroo mobLeaderNearestFemale = mob.getNearestFemaleKangaroo(mob.getMobLeader());
                        mob.getMobLeader().computeNewPosition(false, eps, mobLeaderNearestFemale);
                    }
                }

                // single male kangaroos

                for(int k = 0; k < mob.getSortedMaleKangaroos().size(); k++) {
                    Kangaroo maleKangaroo = mob.getSortedMaleKangaroos().get(k);
                    if(maleKangaroo.getConfrontationFlag() == true) {
                        maleKangaroo.computeNewPosition(true, eps, null);
                    } else {
                        Kangaroo nearestFemale = mob.getNearestFemaleKangaroo(maleKangaroo);
                        maleKangaroo.computeNewPosition(false, eps, nearestFemale);
                    }
                }

                // paired male kangaroos

                for(int k = 0; k < mob.getKangaroosPairList().size(); k++) {
                    Kangaroo maleKangaroo = mob.getKangaroosPairList().get(k).getMale();
                    if(maleKangaroo.getConfrontationFlag() == true) {
                        maleKangaroo.computeNewPosition(true, eps, null);
                    } else {
                        Kangaroo femaleKangaroo = mob.getKangaroosPairList().get(k).getFemale();
                        maleKangaroo.computeNewPosition(false, eps, femaleKangaroo);
                    }
                }

                // single female kangaroos

                for(int k = 0; k < mob.getSortedFemaleKangaroos().size(); k++) {
                    Kangaroo femaleKangaroo = mob.getSortedFemaleKangaroos().get(k);
                    femaleKangaroo.computeNewPosition(false, eps, null);
                }

                // paired female kangaroos

                for(int k = 0; k < mob.getKangaroosPairList().size(); k++) {
                    Kangaroo femaleKangaroo = mob.getKangaroosPairList().get(k).getFemale();
                    Kangaroo maleKangaroo = mob.getKangaroosPairList().get(k).getMale();
                    femaleKangaroo.computeNewPosition(false, eps, maleKangaroo);
                }

                for(int k = 0; k < mob.getKangaroos().size(); k++) {
                    List<Double> newPosition = mob.getKangaroos().get(k).getNewPosition();
                    mob.getKangaroos().get(k).setPosition(new ArrayList<>(newPosition));
                }
            }

            for(int j = 0; j < population.size(); j++) {
                double fitness = of.compute(population.get(j).getPosition());
                population.get(j).setFitness(fitness);
                if(fitness < population.get(j).getLocalBestFitness()) {
                    population.get(j).setLocalBestFitness(fitness);
                    population.get(j).setLocalBest(new ArrayList<>(population.get(j).getPosition()));
                }
                if(fitness < gBest.getFitness()) {
                    gBest = (Kangaroo) SerializationUtils.clone(population.get(j));
                }
            }

            for(int j = 0; j < M; j++) {
                mobs.get(j).kangaroosConfrontation();
            }

            result.setLogs(result.getLogs() + "  Iteration " + (i + 1) + "\n");
            result.setLogs(result.getLogs() + "  GBest = " + gBest.getFitness() + "\n");
        }

        double endTime = System.currentTimeMillis();
        double duration = endTime - startTime;
        result.setGlobalBest(gBest);
        result.setRunningTime(duration);

        return result;
    }

    public Integer getN() {
        return N;
    }

    public void setN(Integer n) {
        N = n;
    }

    public Integer getI() {
        return I;
    }

    public void setI(Integer i) {
        I = i;
    }

    public Integer getD() {
        return D;
    }

    public void setD(Integer d) {
        D = d;
    }

    public Integer getM() {
        return M;
    }

    public void setM(Integer m) {
        M = m;
    }

    public Integer getK() {
        return K;
    }

    public void setK(Integer k) {
        K = k;
    }

    public Integer getsMin() {
        return sMin;
    }

    public void setsMin(Integer sMin) {
        this.sMin = sMin;
    }

    public Integer getsMax() {
        return sMax;
    }

    public void setsMax(Integer sMax) {
        this.sMax = sMax;
    }

    public Integer getCT() {
        return CT;
    }

    public void setCT(Integer CT) {
        this.CT = CT;
    }

    public Double getEps() {
        return eps;
    }

    public void setEps(Double eps) {
        this.eps = eps;
    }

    public OF getOf() {
        return of;
    }

    public void setOf(OF of) {
        this.of = of;
    }

    public Random getRand() {
        return rand;
    }

    public void setRand(Random rand) {
        this.rand = rand;
    }
}
