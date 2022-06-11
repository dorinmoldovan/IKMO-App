package com.ikmo_app.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Mob {

    private List<Kangaroo> kangaroos;
    private List<Kangaroo> sortedMaleKangaroos;
    private List<Kangaroo> sortedFemaleKangaroos;
    private List<KangaroosPair> kangaroosPairList;
    private Kangaroo mobLeader;
    private List<Kangaroo> singleMaleKangaroos;
    private List<Kangaroo> singleFemaleKangaroos;

    public Mob() {
        this.kangaroos = new ArrayList<>();
        this.sortedMaleKangaroos = new ArrayList<>();
        this.sortedFemaleKangaroos = new ArrayList<>();
        this.kangaroosPairList = new ArrayList<>();
        this.singleMaleKangaroos = new ArrayList<>();
        this.singleFemaleKangaroos = new ArrayList<>();
        this.mobLeader = null;
    }

    public void sortKangaroos() {
        for(int i = 0; i < kangaroos.size(); i++) {
            if(kangaroos.get(i).getGender().equals("male")) {
                this.sortedMaleKangaroos.add(kangaroos.get(i));
            } else {
                this.sortedFemaleKangaroos.add(kangaroos.get(i));
            }
        }
        sortedMaleKangaroos.sort(new Comparator<Kangaroo>() {
            @Override
            public int compare(Kangaroo o1, Kangaroo o2) {
                return -o1.getStrength().compareTo(o2.getStrength());
            }
        });
        sortedFemaleKangaroos.sort(new Comparator<Kangaroo>() {
            @Override
            public int compare(Kangaroo o1, Kangaroo o2) {
                return -o1.getStrength().compareTo(o2.getStrength());
            }
        });
    }

    public void distributeKangaroos() {
        if(sortedMaleKangaroos.size() != 0) {
            this.mobLeader = sortedMaleKangaroos.get(0);
            int noMales = sortedMaleKangaroos.size() - 1;
            int noFemales = sortedFemaleKangaroos.size();
            if (noMales > noFemales) {
                for (int i = 0; i < noFemales; i++) {
                    KangaroosPair pair = new KangaroosPair(sortedMaleKangaroos.get(i + 1), sortedFemaleKangaroos.get(i));
                    kangaroosPairList.add(pair);
                }
                for (int i = noFemales; i < noMales; i++) {
                    this.singleMaleKangaroos.add(sortedMaleKangaroos.get(i + 1));
                }
            } else {
                for (int i = 0; i < noMales; i++) {
                    KangaroosPair pair = new KangaroosPair(sortedMaleKangaroos.get(i + 1), sortedFemaleKangaroos.get(i));
                    kangaroosPairList.add(pair);
                }
                for (int i = noMales; i < noFemales; i++) {
                    this.singleFemaleKangaroos.add(sortedFemaleKangaroos.get(i));
                }
            }
        } else {
            int noFemales = sortedFemaleKangaroos.size();
            for (int i = 0; i < noFemales; i++) {
                this.singleFemaleKangaroos.add(sortedFemaleKangaroos.get(i));
            }
        }
    }

    public void kangaroosSniffing() {
        if(kangaroos.size() != 0) {
            double bestLocalFitnessValue = kangaroos.get(0).getLocalBestFitness();
            int bestLocalFitnessValueIndex = 0;
            for(int i = 1; i < kangaroos.size(); i++) {
                if(kangaroos.get(i).getLocalBestFitness() < bestLocalFitnessValue) {
                    bestLocalFitnessValue = kangaroos.get(i).getLocalBestFitness();
                    bestLocalFitnessValueIndex = i;
                }
            }
            for(int i = 0; i < kangaroos.size(); i++) {
                kangaroos.get(i).setLocalBestFitness(bestLocalFitnessValue);
                kangaroos.get(i).setLocalBest(kangaroos.get(bestLocalFitnessValueIndex).getLocalBest());
            }
        }
    }

    public void maleKangaroosSniffing() {
        if(sortedMaleKangaroos.size() != 0) {
            List<Integer> addStrengthValues = new ArrayList<>();
            for(int i = 0; i < sortedMaleKangaroos.size(); i++) {
                int addStrengthValue = 0;
                for(int j = 0; j < sortedMaleKangaroos.size(); j++) {
                    if(i != j) {
                        if(sortedMaleKangaroos.get(i).getStrength() > sortedMaleKangaroos.get(j).getStrength()) {
                            addStrengthValue++;
                        }
                    }
                }
                addStrengthValues.add(addStrengthValue);
            }
            for(int i = 0; i < sortedMaleKangaroos.size(); i++) {
                int newStrengthValue = sortedMaleKangaroos.get(i).getStrength() + addStrengthValues.get(i);
                if(newStrengthValue > sortedMaleKangaroos.get(i).getsMax()) {
                    newStrengthValue = sortedMaleKangaroos.get(i).getsMax();
                }
                sortedMaleKangaroos.get(i).setStrength(newStrengthValue);
            }
        }
    }

    public void kangaroosConfrontation() {
        for(int i = 0; i < this.sortedMaleKangaroos.size(); i++) {
            this.sortedMaleKangaroos.get(i).setConfrontationFlag(false);
        }
        int size = this.sortedMaleKangaroos.size();
        if(size >= 2) {
            Random rand = this.sortedMaleKangaroos.get(0).getRand();
            int index1 = rand.nextInt(size);
            List<Integer> candidates = new ArrayList<Integer>();
            for(int i = 0; i < index1; i++) {
                candidates.add(i);
            }
            for(int i = index1 + 1; i < size; i++) {
                candidates.add(i);
            }
            int index2 = candidates.get(rand.nextInt(size-1));
            int strength1 = sortedMaleKangaroos.get(index1).getStrength();
            int strength2 = sortedMaleKangaroos.get(index2).getStrength();
            int CT = mobLeader.getCT();
            int sMin = mobLeader.getsMin();
            int sMax = mobLeader.getsMax();
            if(Math.abs(strength1 - strength2) <= CT) {
                if(strength1 < strength2) {
                    strength1--;
                    if(strength1 < sMin) {
                        strength1 = sMin;
                    }
                    strength2++;
                    if(strength2 > sMax) {
                        strength2 = sMax;
                    }
                    sortedMaleKangaroos.get(index1).setConfrontationFlag(true);
                } else {
                    strength1++;
                    if(strength1 > sMax) {
                        strength1 = sMax;
                    }
                    strength2--;
                    if(strength2 < sMin) {
                        strength2 = sMin;
                    }
                    sortedMaleKangaroos.get(index2).setConfrontationFlag(true);
                }
                sortedMaleKangaroos.get(index1).setStrength(strength1);
                sortedMaleKangaroos.get(index2).setStrength(strength2);
            }
        }
    }

    public Kangaroo getNearestFemaleKangaroo(Kangaroo kangaroo) {
        Kangaroo nearestFemaleKangaroo = null;

        int index = -1;
        int strength = kangaroo.getsMin() - 1;

        for(int i = 0; i < kangaroosPairList.size(); i++) {
            if(kangaroosPairList.get(i).getMale().getStrength() < kangaroo.getStrength()) {
                if(index == -1) {
                    index = i;
                    strength = kangaroosPairList.get(i).getMale().getStrength();
                } else {
                    if(kangaroosPairList.get(i).getMale().getStrength() > strength) {
                        index = i;
                        strength = kangaroosPairList.get(i).getMale().getStrength();
                    }
                }
            }
        }

        if(index != -1) {
            nearestFemaleKangaroo = kangaroosPairList.get(index).getFemale();
        }

        return nearestFemaleKangaroo;
    }

    public List<Kangaroo> getKangaroos() {
        return kangaroos;
    }

    public void setKangaroos(List<Kangaroo> kangaroos) {
        this.kangaroos = kangaroos;
    }

    public List<Kangaroo> getSortedMaleKangaroos() {
        return sortedMaleKangaroos;
    }

    public void setSortedMaleKangaroos(List<Kangaroo> sortedMaleKangaroos) {
        this.sortedMaleKangaroos = sortedMaleKangaroos;
    }

    public List<Kangaroo> getSortedFemaleKangaroos() {
        return sortedFemaleKangaroos;
    }

    public void setSortedFemaleKangaroos(List<Kangaroo> sortedFemaleKangaroos) {
        this.sortedFemaleKangaroos = sortedFemaleKangaroos;
    }

    public List<KangaroosPair> getKangaroosPairList() {
        return kangaroosPairList;
    }

    public void setKangaroosPairList(List<KangaroosPair> kangaroosPairList) {
        this.kangaroosPairList = kangaroosPairList;
    }

    public Kangaroo getMobLeader() {
        return mobLeader;
    }

    public void setMobLeader(Kangaroo mobLeader) {
        this.mobLeader = mobLeader;
    }

    public List<Kangaroo> getSingleMaleKangaroos() {
        return singleMaleKangaroos;
    }

    public void setSingleMaleKangaroos(List<Kangaroo> singleMaleKangaroos) {
        this.singleMaleKangaroos = singleMaleKangaroos;
    }

    public List<Kangaroo> getSingleFemaleKangaroos() {
        return singleFemaleKangaroos;
    }

    public void setSingleFemaleKangaroos(List<Kangaroo> singleFemaleKangaroos) {
        this.singleFemaleKangaroos = singleFemaleKangaroos;
    }
}
