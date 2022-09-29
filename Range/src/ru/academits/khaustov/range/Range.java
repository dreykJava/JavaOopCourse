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

    public double getLength() {
        return to - from;
    }

    public boolean isInside(double number) {
        return number >= from && number <= to;
    }

    @Override
    public String toString() {
        return "Начало диапазона: " + from + ". "
                + "Конец диапазона: " + to + ". "
                + "Длина диапазона равна: " + getLength() + ". ";
    }

    public Range getIntersection(Range range) {
        if (this.to <= range.from || range.to <= this.from) {
            return null;
        }

        return new Range(Math.max(this.from, range.from), Math.min(this.to, range.to));
    }

    public Range[] getUnion(Range range) {
        if (this.to < range.from || range.to < this.from) {
            return new Range[] {
                    new Range(this.from, this.to),
                    new Range(range.from, range.to)
            };
        }

        return new Range[] {
                new Range(Math.min(this.from, range.from), Math.max(this.to, range.to))
        };
    }

    public Range[] getDifference(Range range) {
        if (this.to <= range.to && this.from >= range.from) {
            return new Range[] {};
        }

        if (this.to <= range.from || this.from >= range.to) {
            return new Range[] {
                    new Range(this.from, this.to)
            };
        }

        if (this.from <= range.from && this.to <= range.to) {
            return new Range[] {
                    new Range(this.from, range.from)
            };
        }

        if (this.from > range.from) {
            return new Range[] {
                    new Range(range.to, this.to)
            };
        }

        return new Range[] {
                new Range(this.from, range.from),
                new Range(range.to, this.to)
        };
    }
}