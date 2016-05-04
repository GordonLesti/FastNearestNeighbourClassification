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
fnnc.algo.FullSearch:
	PreProcessing: 0.0
	QueryProcessing: 50.0
fnnc.algo.OrchardsAlgorithm:
	PreProcessing: 1225.0
	QueryProcessing: 14.514
fnnc.algo.AnnulusMethod:
	PreProcessing: 50.0
	QueryProcessing: 25.246
fnnc.algo.Aesa:
	PreProcessing: 1225.0
	QueryProcessing: 4.212
```
The file can be loaded later without the parameters above.
```
$ java -jar build/libs/fnnc-1.0.jar data/50-500.ser
Size: 50
Count: 500
fnnc.algo.FullSearch:
	PreProcessing: 0.0
	QueryProcessing: 50.0
fnnc.algo.OrchardsAlgorithm:
	PreProcessing: 1225.0
	QueryProcessing: 14.444
fnnc.algo.AnnulusMethod:
	PreProcessing: 50.0
	QueryProcessing: 25.962
fnnc.algo.Aesa:
	PreProcessing: 1225.0
	QueryProcessing: 4.2
```

## Test

```
$ gradle check
```
