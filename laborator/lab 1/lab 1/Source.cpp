#include <iostream>
#include <vector>
#include <fstream>
using namespace std;

class asterix_matrix {
private: 
	int width,length;
	vector<int> array;
public:
	asterix_matrix();
	void display_matrix();
	void read_matrix_from_file();
};

asterix_matrix::asterix_matrix() {
	width = 0;
	length = 0;
}
void asterix_matrix::display_matrix() {
	for (int i = 0; i < this->length; i++)
	{
		cout << "|  ";
		for (int j = 0; j < this->width; j++) {
			if (this->array[i*length + width] == 1)
				cout << "* ";
			else
				cout << "  ";
		}
	}
	cout << endl;
	cout << "+--";
	for (int i = 0; i < this->width*2; i++)
	{
		cout << "-";
	}
}
void asterix_matrix::read_matrix_from_file(){
	ifstream myfile;
	myfile.open("Text.txt");
	char auxiliary;
	while (EOF != (auxiliary = getchar())) {
		if (auxiliary == '\n') {
			length++;
			width = 0;
		}
		else if (auxiliary == '1') {
			this->array.push_back(1);
			width++;
		}
		else if (auxiliary == '0') {
			this->array.push_back(0);
			width++;
		}
	}
}

int main()
{
	asterix_matrix matrice;
	matrice.read_matrix_from_file();
	matrice.display_matrix();
	return 0;
}