# Fast Nearest Neighbour Classification - Program

A program to benchmark the calls of a distance function in one of the following algorithms:
* Full Search
* Orchards Algorithm
* Annulus Method

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
PreProcessing and MainProcessing.

```
$ java -jar build/libs/fnnc-1.0.jar data/50-500.ser 50 500
Size: 50
Count: 500
fnnc.algo.FullSearch:
	PreProcessing: 0.0
	MainProcessing: 50.0
fnnc.algo.OrchardsAlgorithm:
	PreProcessing: 1225.0
	MainProcessing: 19.346
fnnc.algo.AnnulusMethod:
	PreProcessing: 50.0
	MainProcessing: 26.166
```
The file can be loaded later without the parameters above.
```
$ java -jar build/libs/fnnc-1.0.jar data/50-500.ser
Size: 50
Count: 500
fnnc.algo.FullSearch:
	PreProcessing: 0.0
	MainProcessing: 50.0
fnnc.algo.OrchardsAlgorithm:
	PreProcessing: 1225.0
	MainProcessing: 19.674
fnnc.algo.AnnulusMethod:
	PreProcessing: 50.0
	MainProcessing: 26.604

```

## Test

```
$ gradle check
```
