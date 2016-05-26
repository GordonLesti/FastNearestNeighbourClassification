# Fast Nearest Neighbour Classification - Program

A program to benchmark the calls of a distance function in one of the following algorithms:
* Full Search
* Orchards Algorithm
* Annulus Method
* AESA

## Requirements

* [Java](http://openjdk.java.net/) >= 1.7
* [Gradle](https://gradle.org/) >= 1.5

## Install

```
$ gradle build
```

## Run

The following command will write 500 sets of 50 `java.awt.geom.Point2D` test points and one query point to the file
`data/50-500.ser`. After that it will run every algorithm against this test data and will return the mean for
PreProcessing and QueryProcessing.

```
$ java -jar build/libs/fnnc-1.0.jar data/50-500.ser 50 500
Size: 50
Count: 500
FullSearch:
	PreProcessing:
		Mean: 0.0
		StandardDeviation: 0.0
	QueryProcessing:
		Mean: 50.0
		StandardDeviation: 0.0
Orchard:
	PreProcessing:
		Mean: 1225.0
		StandardDeviation: 0.0
	QueryProcessing:
		Mean: 20.862
		StandardDeviation: 10.110932499032911
Orchard MarkBits:
	PreProcessing:
		Mean: 1225.0
		StandardDeviation: 0.0
	QueryProcessing:
		Mean: 14.754
		StandardDeviation: 6.038996936578126
Annulus:
	PreProcessing:
		Mean: 50.0
		StandardDeviation: 0.0
	QueryProcessing:
		Mean: 26.69
		StandardDeviation: 11.845247992338535
AESA:
	PreProcessing:
		Mean: 1225.0
		StandardDeviation: 0.0
	QueryProcessing:
		Mean: 4.288
		StandardDeviation: 0.8538477616062469
```
The file can be loaded later without the parameters above.
```
$ java -jar build/libs/fnnc-1.0.jar data/50-500.ser
Size: 50
Count: 500
FullSearch:
	PreProcessing:
		Mean: 0.0
		StandardDeviation: 0.0
	QueryProcessing:
		Mean: 50.0
		StandardDeviation: 0.0
Orchard:
	PreProcessing:
		Mean: 1225.0
		StandardDeviation: 0.0
	QueryProcessing:
		Mean: 19.84
		StandardDeviation: 9.908905085830623
Orchard MarkBits:
	PreProcessing:
		Mean: 1225.0
		StandardDeviation: 0.0
	QueryProcessing:
		Mean: 14.55
		StandardDeviation: 6.218962936052928
Annulus:
	PreProcessing:
		Mean: 50.0
		StandardDeviation: 0.0
	QueryProcessing:
		Mean: 26.74
		StandardDeviation: 11.111633543273465
AESA:
	PreProcessing:
		Mean: 1225.0
		StandardDeviation: 0.0
	QueryProcessing:
		Mean: 4.236
		StandardDeviation: 0.8810811540374707
```

## Test

```
$ gradle check
```
