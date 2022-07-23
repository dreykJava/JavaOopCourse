package ru.academits.khaustov.range;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double getFrom() {
        return from;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public double getTo() {
        return to;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public double getRangeLength() {
        return to - from;
    }

    public boolean isInside(double number) {
        return number >= from && number <= to;
    }

    public double[] getIntervalsIntersection(Range range2) {
        double from1 = from;
        double to1 = to;
        double from2 = range2.getFrom();
        double to2 = range2.getTo();

        if (to1 <= from2 || to2 <= from1) {
            return null;
        }

        double[] intersection = new double[2];
        intersection[0] = Math.max(from1, from2);
        intersection[1] = Math.min(to1, to2);

        return intersection;
    }

    public Range[] getIntervalsUnion(Range range1, Range range2) {
        double from1 = range1.getFrom();
        double to1 = range1.getTo();
        double from2 = range2.getFrom();
        double to2 = range2.getTo();
        Range[] union;

        if (to1 < from2 || to2 < from1) {
            union = new Range[2];
            union[0] = range1;
            union[1] = range2;

            return union;
        }

        union = new Range[1];
        Range unionRange = new Range(Math.min(from1, from2), Math.max(to1, to2));
        union[0] = unionRange;

        return union;
    }

    public Range[] getIntervalsDifference(Range range1, Range range2) {
        double from1 = range1.getFrom();
        double to1 = range1.getTo();
        double from2 = range2.getFrom();
        double to2 = range2.getTo();
        Range[] difference;
        Range differenceRange;

        if (to1 <= to2 && from1 >= from2) {
            return null;
        } else if (from1 < from2 && to1 <= to2) {
            differenceRange = new Range(from1, from2);

            difference = new Range[1];
            difference[0] = differenceRange;

            return difference;
        } else if (from1 >= from2) {
            differenceRange = new Range(to2, to1);

            difference = new Range[1];
            difference[0] = differenceRange;

            return difference;
        }

        Range differenceRange1 = new Range(from1, from2);
        Range differenceRange2 = new Range(to2, to1);

        difference = new Range[2];
        difference[0] = differenceRange1;
        difference[1] = differenceRange2;

        return difference;
    }
}
