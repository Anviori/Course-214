/*
	Wilmer Suarez
	109592501
	ESE 344
	Project 1
*/

#include <iostream>
#include <iomanip>
#include <cstdlib> 
#include <ctime>   
#include <assert.h>
#include <math.h>    
#include <iomanip>

#define PI 3.14159265358979323846

using std::cout;
using std::cin;
using std::endl;

/*
	Struct used to hold the real and imaginary parts of
	complex numbers.
*/
struct cmpx { 
	double rl; 
	double im; 
};

/*
	Function allocates memory for 3D array.
*/
struct cmpx*** malloc_3D(int m1, int m2, int m3) {
	struct cmpx*** data3D = new struct cmpx**[m1];
	for (int i = 0; i < m1; i++) {
		data3D[i] = new struct cmpx*[m2];
		for (int j = 0; j < m2; j++) {
			data3D[i][j] = new struct cmpx[m3];
			for (int k = 0; k < m3; k++) {
				data3D[i][j][k].rl = 0;
				data3D[i][j][k].im = 0;
			}
		}
	}
	return data3D;
}

/*
	Function allocates memory for 2D array.
*/
struct cmpx** malloc_2D(int m1, int m2) {
	struct cmpx** data2D = new struct cmpx*[m1];
	for (int i = 0; i < m1; i++) {
		data2D[i] = new struct cmpx[m2];
		for (int j = 0; j < m2; j++) {
			data2D[i][j].rl = 0;
			data2D[i][j].im = 0;
		}
	}
	return data2D;
}

/*
	Function allocates memory for 1D array.
*/
struct cmpx* malloc_1D(int m1) {
	struct cmpx* data1D = new struct cmpx[m1];
	for (int i = 0; i < m1; i++) {
		data1D[i].rl = 0;
		data1D[i].im = 0;
	}
	return data1D;
}

/*
	Function generates data to populate 3D digital signal array
	and prints the data out.
*/
struct cmpx*** generatePrint_data_3D(struct cmpx*** data3D, int m1, int m2, int m3) {

	srand((unsigned int) time(NULL));
	for (int i = 0; i < m1; i++) {
		for (int j = 0; j < m2; j++) {
			for (int k = 0; k < m3; k++) {
				data3D[i][j][k].rl = (float)(rand() % 256); // limit data to 0 to 255.
				data3D[i][j][k].im = (float)(rand() % 256); // limit data to 0 to 255.
			}
		}
	}

	// Print data
	std::cout << "\nData generated: " << std::endl;
	for (int i = 0; i < m1; i++) {
		for (int j = 0; j < m2; j++) {
			for (int k = 0; k < m3; k++) {
				std::cout << data3D[i][j][k].rl << ' ' << data3D[i][j][k].im << '\t' << '\t';
			}
			std::cout << std::endl;
		}
		std::cout << std::endl;
	}
	return data3D;
}

/*
	Function generates data to populate 2D digital signal array.
*/
struct cmpx** generatePrint_data_2D(struct cmpx** data2D, int m1, int m2) {

	srand((unsigned int) time(NULL));
	for (int i = 0; i < m1; i++) {
		for (int j = 0; j < m2; j++) {
			data2D[i][j].rl = (float)(rand() % 256); // limit data to 0 to 255.
			data2D[i][j].im = (float)(rand() % 256); // limit data to 0 to 255.
		}
	}

	// Print data
	std::cout << "\nData generated: " << std::endl;
	for (int i = 0; i < m1; i++) {
		for (int j = 0; j < m2; j++) {
			std::cout << data2D[i][j].rl << ' ' << data2D[i][j].im << '\t' << '\t';
		}
		std::cout << std::endl;
	}
	return data2D;
}

/*
	Function generates data to populate 2D digital signal array.
*/
struct cmpx* generatePrint_data_1D(struct cmpx* data1D, int m1) {

	srand((unsigned int) time(NULL));
	for (int i = 0; i < m1; i++) {
		data1D[i].rl = (float)(rand() % 256); // limit data to 0 to 255.
		data1D[i].im = (float)(rand() % 256); // limit data to 0 to 255.
	}

	// Print data
	std::cout << "\nData generated: " << std::endl;
	for (int i = 0; i < m1; i++) {
		std::cout << data1D[i].rl << ' ' << data1D[i].im << '\t';
	}
	std::cout << std::endl;
	return data1D;
}

/*
	Function computes and prints the FDFT of the 3D signal. 
*/
struct cmpx*** compute_FDFT3D(struct cmpx*** FDFT3D, struct cmpx*** data3D, int m1, int m2, int m3) {
	struct cmpx*** FDFT3D_1 = malloc_3D(m1, m2, m3);
	struct cmpx*** FDFT3D_2 = malloc_3D(m1, m2, m3);
	for (int x1 = 0; x1 < m1; x1++) {
		for (int x2 = 0; x2 < m2; x2++) {
			for (int u3 = 0; u3 < m3; u3++) {
				for (int x3 = 0; x3 < m3; x3++) {
					double angle = -2 * PI * u3 * x3 / m3;
					FDFT3D_1[x1][x2][u3].rl += data3D[x1][x2][x3].rl * cos(angle) - data3D[x1][x2][x3].im * sin(angle);
					FDFT3D_1[x1][x2][u3].im += data3D[x1][x2][x3].rl * sin(angle) + data3D[x1][x2][x3].im * cos(angle);
				}
			}
		}
	}
	for (int x1 = 0; x1 < m1; x1++) {
		for (int u2 = 0; u2 < m2; u2++) {
			for (int u3 = 0; u3 < m3; u3++) {
				for (int x2 = 0; x2 < m2; x2++) {
					double angle = -2 * PI * u2 * x2 / m2;
					FDFT3D_2[x1][u2][u3].rl += FDFT3D_1[x1][x2][u3].rl * cos(angle) - FDFT3D_1[x1][x2][u3].im * sin(angle);
					FDFT3D_2[x1][u2][u3].im += FDFT3D_1[x1][x2][u3].rl * sin(angle) + FDFT3D_1[x1][x2][u3].im * cos(angle);
				}
			}
		}
	}
	for (int u1 = 0; u1 < m1; u1++) {
		for (int u2 = 0; u2 < m2; u2++) {
			for (int u3 = 0; u3 < m3; u3++) {
				for (int x1 = 0; x1 < m1; x1++) {
					double angle = -2 * PI * u1 * x1 / m1;
					FDFT3D[u1][u2][u3].rl += FDFT3D_2[x1][u2][u3].rl * cos(angle) - FDFT3D_2[x1][u2][u3].im * sin(angle);
					FDFT3D[u1][u2][u3].im += FDFT3D_2[x1][u2][u3].rl * sin(angle) + FDFT3D_2[x1][u2][u3].im * cos(angle);
				}
			}
		}
	}

	// Print FDFT
	cout << "\nFDFT_3D: " << endl;
	for (int i = 0; i < m1; i++) {
		for (int j = 0; j < m2; j++) {
			for (int k = 0; k < m3; k++) {
				printf("%8.2f %8.2f     ", FDFT3D[i][j][k].rl, FDFT3D[i][j][k].im);
			}
			cout << endl;
		}
		cout << endl;
	}
	return FDFT3D;
}

/*
	Function computes and prints the FDFT of the 2D signal.
*/
struct cmpx** compute_FDFT2D(struct cmpx** FDFT2D, struct cmpx** data2D, int m1, int m2) {
	struct cmpx** FDFT2D_1 = malloc_2D(m1, m2);
	for (int x1 = 0; x1 < m1; x1++) {
		for (int u2 = 0; u2 < m2; u2++) {
				for (int x2 = 0; x2 < m2; x2++) {
					double angle = -2 * PI * u2 * x2 / m2;
					FDFT2D_1[x1][u2].rl += data2D[x1][x2].rl * cos(angle) - data2D[x1][x2].im * sin(angle);
					FDFT2D_1[x1][u2].im += data2D[x1][x2].rl * sin(angle) + data2D[x1][x2].im * cos(angle);
				}
		}
	}
	for (int u1 = 0; u1 < m1; u1++) {
		for (int u2 = 0; u2 < m2; u2++) {
				for (int x1 = 0; x1 < m1; x1++) {
					double angle = -2 * PI * u1 * x1 / m1;
					FDFT2D[u1][u2].rl += FDFT2D_1[x1][u2].rl * cos(angle) - FDFT2D_1[x1][u2].im * sin(angle);
					FDFT2D[u1][u2].im += FDFT2D_1[x1][u2].rl * sin(angle) + FDFT2D_1[x1][u2].im * cos(angle);
				}
		}
	}

	// Print FDFT
	cout << "\nFDFT_2D: " << endl;
	for (int i = 0; i < m1; i++) {
		for (int j = 0; j < m2; j++) {
			printf("%8.2f %8.2f     ", FDFT2D[i][j].rl, FDFT2D[i][j].im);
		}
		cout << endl;
	}
	return FDFT2D;
}

/*
	Function computes and prints the FDFT of the 1D signal.
*/
struct cmpx* compute_FDFT1D(struct cmpx* FDFT1D, struct cmpx* data1D, int m1) {
	for (int u1 = 0; u1 < m1; u1++) {
		for (int x1 = 0; x1 < m1; x1++) {
			double angle = -2 * PI * u1 * x1 / m1;
			FDFT1D[u1].rl += data1D[x1].rl * cos(angle) - data1D[x1].im * sin(angle);
			FDFT1D[u1].im += data1D[x1].rl * sin(angle) + data1D[x1].im * cos(angle);
		}
	}
	
	// Print FDFT
	cout << "\nFDFT_1D: " << endl;
	for (int i = 0; i < m1; i++) {
		printf("%5.2f %5.2f     ", FDFT1D[i].rl, FDFT1D[i].im);
	}
	cout << endl;
	return FDFT1D;
}

/*
	Function computes and prints the IDFT of the 3D signal.
*/
struct cmpx*** compute_IDFT3D(struct cmpx*** IDFT3D, struct cmpx*** FDFT3D, int m1, int m2, int m3, double inverse) {
	struct cmpx*** IDFT3D_1 = malloc_3D(m1, m2, m3);
	struct cmpx*** IDFT3D_2 = malloc_3D(m1, m2, m3);
	for (int x1 = 0; x1 < m1; x1++) {
		for (int x2 = 0; x2 < m2; x2++) {
			for (int u3 = 0; u3 < m3; u3++) {
				for (int x3 = 0; x3 < m3; x3++) {
					double angle = 2 * PI * u3 * x3 / m3;
					IDFT3D_1[x1][x2][u3].rl += FDFT3D[x1][x2][x3].rl * cos(angle) - FDFT3D[x1][x2][x3].im * sin(angle);
					IDFT3D_1[x1][x2][u3].im += FDFT3D[x1][x2][x3].rl * sin(angle) + FDFT3D[x1][x2][x3].im * cos(angle);
				}
			}
		}
	}
	for (int x1 = 0; x1 < m1; x1++) {
		for (int u2 = 0; u2 < m2; u2++) {
			for (int u3 = 0; u3 < m3; u3++) {
				for (int x2 = 0; x2 < m2; x2++) {
					double angle = 2 * PI * u2 * x2 / m2;
					IDFT3D_2[x1][u2][u3].rl += IDFT3D_1[x1][x2][u3].rl * cos(angle) - IDFT3D_1[x1][x2][u3].im * sin(angle);
					IDFT3D_2[x1][u2][u3].im += IDFT3D_1[x1][x2][u3].rl * sin(angle) + IDFT3D_1[x1][x2][u3].im * cos(angle);
				}
			}
		}
	}
	for (int u1 = 0; u1 < m1; u1++) {
		for (int u2 = 0; u2 < m2; u2++) {
			for (int u3 = 0; u3 < m3; u3++) {
				for (int x1 = 0; x1 < m1; x1++) {
					double angle = 2 * PI * u1 * x1 / m1;
					IDFT3D[u1][u2][u3].rl += IDFT3D_2[x1][u2][u3].rl * cos(angle) - IDFT3D_2[x1][u2][u3].im * sin(angle);
					IDFT3D[u1][u2][u3].im += IDFT3D_2[x1][u2][u3].rl * sin(angle) + IDFT3D_2[x1][u2][u3].im * cos(angle);
				}
				IDFT3D[u1][u2][u3].rl *= inverse;
				IDFT3D[u1][u2][u3].im *= inverse;
			}
		}
	}

	// Print IFDFT
	cout << "\nIDFT_3D: " << endl;
	for (int i = 0; i < m1; i++) {
		for (int j = 0; j < m2; j++) {
			for (int k = 0; k < m3; k++) {
				printf("%8.2f %8.2f     ", IDFT3D[i][j][k].rl, IDFT3D[i][j][k].im);
			}
			cout << endl;
		}
		cout << endl;
	}
	return IDFT3D;
}

/*
	Function computes and prints the IDFT of the 2D signal.
*/
struct cmpx** compute_IDFT2D(struct cmpx** IDFT2D, struct cmpx** FDFT2D, int m1, int m2, double inverse) {
	struct cmpx** IDFT2D_1 = malloc_2D(m1, m2);
	for (int x1 = 0; x1 < m1; x1++) {
		for (int u2 = 0; u2 < m2; u2++) {
			for (int x2 = 0; x2 < m2; x2++) {
				double angle = 2 * PI * u2 * x2 / m2;
				IDFT2D_1[x1][u2].rl += FDFT2D[x1][x2].rl * cos(angle) - FDFT2D[x1][x2].im * sin(angle);
				IDFT2D_1[x1][u2].im += FDFT2D[x1][x2].rl * sin(angle) + FDFT2D[x1][x2].im * cos(angle);
			}
		}
	}
	for (int u1 = 0; u1 < m1; u1++) {
		for (int u2 = 0; u2 < m2; u2++) {
			for (int x1 = 0; x1 < m1; x1++) {
				double angle = 2 * PI * u1 * x1 / m1;
				IDFT2D[u1][u2].rl += IDFT2D_1[x1][u2].rl * cos(angle) - IDFT2D_1[x1][u2].im * sin(angle);
				IDFT2D[u1][u2].im += IDFT2D_1[x1][u2].rl * sin(angle) + IDFT2D_1[x1][u2].im * cos(angle);
			}
			IDFT2D[u1][u2].rl *= inverse;
			IDFT2D[u1][u2].im *= inverse;
		}
	}

	// Print IFDFT
	cout << "\nIFDFT_2D: " << endl;
	for (int i = 0; i < m1; i++) {
		for (int j = 0; j < m2; j++) {
			printf("%8.2f %8.2f     ", IDFT2D[i][j].rl, IDFT2D[i][j].im);
		}
		cout << endl;
	}
	return IDFT2D;
}

/*
	Function computes and prints the IDFT of the 1D signal.
*/
struct cmpx* compute_IDFT1D(struct cmpx* IDFT1D, struct cmpx* FDFT1D, int m1, double inverse) {
	for (int u1 = 0; u1 < m1; u1++) {
		for (int x1 = 0; x1 < m1; x1++) {
			double angle = 2 * PI * u1 * x1 / m1;
			IDFT1D[u1].rl += FDFT1D[x1].rl * cos(angle) - FDFT1D[x1].im * sin(angle);
			IDFT1D[u1].im += FDFT1D[x1].rl * sin(angle) + FDFT1D[x1].im * cos(angle);
		}
		IDFT1D[u1].rl *= inverse;
		IDFT1D[u1].im *= inverse;
	}

	// Print IFDFT
	cout << "\nIDFT_1D: " << endl;
	for (int i = 0; i < m1; i++) {
		printf("%5.2f %5.2f     ", IDFT1D[i].rl, IDFT1D[i].im);
	}
	cout << endl;
	return IDFT1D;
}

/*
	Function computes RMS error between the original digital signal and the
	3 dimensional inverse DFT.
*/
void rms_3D(struct cmpx*** IDFT3D, struct cmpx*** data3D, int m1, int m2, int m3) {
	double sum = 0, arg1 = 0, arg2 = 0, RMSE_3D = 0;
	for (int i = 0; i < m1; i++) {
		for (int k = 0; k < m2; k++) {
			for (int l = 0; l < m3; l++) {
				arg1 = pow((data3D[i][k][l].rl - IDFT3D[i][k][l].rl), 2);
				arg2 = pow((data3D[i][k][l].im - IDFT3D[i][k][l].im), 2);
				sum += arg1 + arg2;
			}
		}
	}

	RMSE_3D = sqrt(sum / (m1 * m2 * m3));

	printf("\nRMSE_3D: %.2f\n\n", RMSE_3D);
}

/*
	Function computes RMS error between the original digital signal and the
	2 dimensional inverse DFT.
*/
void rms_2D(struct cmpx** IDFT2D, struct cmpx** data2D, int m1, int m2) {
	double sum = 0, arg1 = 0, arg2 = 0, RMSE_2D = 0;
	for (int i = 0; i < m1; i++) {
		for (int k = 0; k < m2; k++) {
			arg1 = pow((data2D[i][k].rl - IDFT2D[i][k].rl), 2);
			arg2 = pow((data2D[i][k].im - IDFT2D[i][k].im), 2);
			sum += arg1 + arg2;
		}
	}

	RMSE_2D = sqrt(sum / (m1 * m2));

	printf("\nRMSE_2D: %.2f\n\n", RMSE_2D);
}

/*
	Function computes RMS error between the original digital signal and the
	1 dimensional inverse DFT.
*/
void rms_1D(struct cmpx* IDFT1D, struct cmpx* data1D, int m1) {
	double sum = 0, arg1 = 0, arg2 = 0, RMSE_1D = 0;
	for (int i = 0; i < m1; i++) {
		arg1 = pow((data1D[i].rl - IDFT1D[i].rl), 2);
		arg2 = pow((data1D[i].im - IDFT1D[i].im), 2);
		sum += arg1 + arg2;
	}

	RMSE_1D = sqrt(sum / m1);

	printf("\nRMSE_1D: %.2f\n\n", RMSE_1D);
}

int main() {

	int dimensions, m1, m2, m3;
	double inverse;
	cout << "Enter dimensions of the signal: ";
	cin >> dimensions;
	
	assert(dimensions > 0);		// Exit if dimensions is non-positive
	assert(dimensions <= 3);	// Exit if greater than 4-dimensions

	// Get dimension sizes form user
	if (dimensions == 1 || dimensions == 2 || dimensions == 3) {
		cout << "Enter 1st dimension size: ";
		cin >> m1;
		if (dimensions == 2 || dimensions == 3) {
			cout << "Enter 2nd dimension size: ";
			cin >> m2;
			if (dimensions == 3) {
				cout << "Enter 3rd dimension size: ";
				cin >> m3;
			}
		}
	}		

	// Allocate memory for the digital signal, Forward DFT, and Inverse DFT
	// Generate data for digital signal and print it out
	if(dimensions == 3) {
		struct cmpx*** data3D;
		struct cmpx*** FDFT3D;
		struct cmpx*** IDFT3D;

		data3D = malloc_3D(m1, m2, m3);
		data3D = generatePrint_data_3D(data3D, m1, m2, m3);

		FDFT3D = malloc_3D(m1, m2, m3);
		IDFT3D = malloc_3D(m1, m2, m3);

		// Compute and print FDFT
		FDFT3D = compute_FDFT3D(FDFT3D, data3D, m1, m2, m3);

		inverse = 1 / ((double) (m1 * m2 * m3)); // Constant used to multiply IDFT

		// Compute and print IDFT
		IDFT3D = compute_IDFT3D(IDFT3D, FDFT3D, m1, m2, m3, inverse);

		// Compute and print RMS error
		rms_3D(IDFT3D, data3D, m1, m2, m3);

		// Deallocate memory
		for (int i = 0; i < m1; i++) {
			for (int j = 0; j < m2; j++) {
				delete[](data3D[i][j]);
				delete[](FDFT3D[i][j]);
				delete[](IDFT3D[i][j]);
			}
		}
		for (int i = 0; i < m1; i++) {
			delete[](data3D[i]);
			delete[](FDFT3D[i]);
			delete[](IDFT3D[i]);
		}

		delete[] data3D;
		delete[] FDFT3D;
		delete[] IDFT3D;
	}
	else if (dimensions == 2) {
		struct cmpx** data2D;
		struct cmpx** FDFT2D;
		struct cmpx** IDFT2D;

		data2D = malloc_2D(m1, m2);
		data2D = generatePrint_data_2D(data2D, m1, m2);

		FDFT2D = malloc_2D(m1, m2);
		IDFT2D = malloc_2D(m1, m2);

		// Compute and print FDFT
		FDFT2D = compute_FDFT2D(FDFT2D, data2D, m1, m2);

		inverse = 1 / ((double) (m1 * m2)); // Constant used to multiply IDFT

		// Compute and print IDFT
		IDFT2D = compute_IDFT2D(IDFT2D, FDFT2D, m1, m2, inverse);

		// Compute and print RMS error
		rms_2D(IDFT2D, data2D, m1, m2);

		// Deallocate memory
		for (int i = 0; i < m1; i++) {
			delete[](data2D[i]);
			delete[](FDFT2D[i]);
			delete[](IDFT2D[i]);
		}

		delete[] data2D;
		delete[] FDFT2D;
		delete[] IDFT2D;
	}
	else {
		struct cmpx* data1D = 0;
		struct cmpx* FDFT1D = 0;
		struct cmpx* IDFT1D = 0;

		data1D = malloc_1D(m1);
		data1D = generatePrint_data_1D(data1D, m1);

		FDFT1D = malloc_1D(m1);
		IDFT1D = malloc_1D(m1);

		// Compute and print FDFT
		FDFT1D = compute_FDFT1D(FDFT1D, data1D, m1);

		inverse = 1 / ((double) m1); // Constant used to multiply IDFT

		// Compute and print IDFT
		IDFT1D = compute_IDFT1D(IDFT1D, FDFT1D, m1, inverse);

		// Compute and print RMS error
		rms_1D(IDFT1D, data1D, m1);

		// Deallocate memory
		delete[] data1D;
		delete[] FDFT1D;
		delete[] IDFT1D;
	}

	system("PAUSE");
	return 0;
}
