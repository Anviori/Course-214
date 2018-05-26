Multi-dimensional Discrete Fourier Transform
======
Dynamically Allocated Multi-dimensional Arrays
======

Introduction:
======

In this project, dinamically allocated arrays are used to store and compute the forward and inverse 
Discrete Fourier Transform for signals of up to 3 dimensions.
This can be used for:

    - Digital Audio (1 Dimension)
    - Image (2 Dimensions) 
    - Video (3 Dimensions)

The memories for the arrays were determined at runtime (by the user) and were dynamically allocated. The seperable 
property of the DFT (for all 3 dimensions) is used in computing the Transforms to help saze space and make the 
computetions faster. 