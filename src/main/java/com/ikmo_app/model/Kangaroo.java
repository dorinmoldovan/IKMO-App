package com.ikmo_app.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Kangaroo implements Serializable {

    private List<Double> position;
    private List<Double> velocity;
    private Integer D;
    private String gender;
    private Integer strength;
    private Double pMin;
    private Double pMax;
    private Integer sMin;
    private Integer sMax;
    private List<Double> localBest;
    private Random rand;
    private Double fitness;
    private Double localBestFitness;
    private Boolean confrontationFlag;
    private Integer CT;
    private Double eps;
    private List<Double> newPosition;

    public Kangaroo(Random rand, Integer d, Double pMin, Double pMax, Integer sMin, Integer sMax, Integer CT, Double eps) {
        this.rand = rand;
        this.D = d;
        this.pMin = pMin;
        this.pMax = pMax;
        this.position = new ArrayList<Double>();
        this.localBest = new ArrayList<Double>();
        this.velocity = new ArrayList<Double>();
        for(int i = 0; i < this.D; i++) {
            double value = (pMax - pMin) * rand.nextDouble() + pMin;
            this.position.add(value);
            this.localBest.add(value);
            int vVal = rand.nextInt(3);
            if(vVal == 0) {
                this.velocity.add(1.0);
            } else if(vVal == 1) {
                this.velocity.add(1.5);
            } else {
                this.velocity.add(2.0);
            }
        }
        int gVal = rand.nextInt(2);
        if(gVal == 0) {
            this.gender = "male";
        } else {
            this.gender = "female";
        }
        this.sMin = sMin;
        this.sMax = sMax;
        this.strength = sMin + rand.nextInt(sMax) + 1;
        this.confrontationFlag = false;
        this.CT = CT;
        this.eps = eps;
    }

    public List<Double> computeNewPosition(boolean confrontationFlag, double eps, Kangaroo partner) {
        newPosition = new ArrayList<Double>();;
        if(confrontationFlag == true) {
            for(int i = 0; i < this.D; i++) {
                double newValue = this.position.get(i) * rand.nextDouble();
                if(newValue < pMin) {
                    newValue = pMin;
                }
                if(newValue > pMax) {
                    newValue = pMax;
                }
                newPosition.add(newValue);
            }
        } else {
            double sd = Math.exp(-(fitness - localBestFitness) * 1.0/(fitness + eps));
            for(int i = 0; i < this.D; i++) {
                double newValue = this.position.get(i);
                newValue += (this.position.get(i) * rand.nextDouble() * rand.nextGaussian() * sd);
                if(partner != null) {
                    newValue += this.velocity.get(i) * rand.nextDouble() * (partner.getPosition().get(i) - this.position.get(i));
                }
                if(newValue < pMin) {
                    newValue = pMin;
                }
                if(newValue > pMax) {
                    newValue = pMax;
                }
                newPosition.add(newValue);
            }
        }
        return newPosition;
    }

    public List<Double> getPosition() {
        return position;
    }

    public void setPosition(List<Double> position) {
        this.position = position;
    }

    public List<Double> getVelocity() {
        return velocity;
    }

    public void setVelocity(List<Double> velocity) {
        this.velocity = velocity;
    }

    public Integer getD() {
        return D;
    }

    public void setD(Integer d) {
        D = d;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getStrength() {
        return strength;
    }

    public void setStrength(Integer strength) {
        this.strength = strength;
    }

    public Double getpMin() {
        return pMin;
    }

    public void setpMin(Double pMin) {
        this.pMin = pMin;
    }

    public Double getpMax() {
        return pMax;
    }

    public void setpMax(Double pMax) {
        this.pMax = pMax;
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

    public List<Double> getLocalBest() {
        return localBest;
    }

    public void setLocalBest(List<Double> localBest) {
        this.localBest = localBest;
    }

    public Random getRand() {
        return rand;
    }

    public void setRand(Random rand) {
        this.rand = rand;
    }

    public Double getFitness() {
        return fitness;
    }

    public void setFitness(Double fitness) {
        this.fitness = fitness;
    }

    public Double getLocalBestFitness() {
        return localBestFitness;
    }

    public void setLocalBestFitness(Double localBestFitness) {
        this.localBestFitness = localBestFitness;
    }

    public Boolean getConfrontationFlag() {
        return confrontationFlag;
    }

    public void setConfrontationFlag(Boolean confrontationFlag) {
        this.confrontationFlag = confrontationFlag;
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

    public List<Double> getNewPosition() {
        return newPosition;
    }

    public void setNewPosition(List<Double> newPosition) {
        this.newPosition = newPosition;
    }
}
