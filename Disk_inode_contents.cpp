#include <iostream>
#include <sys/stat.h>
#include <cstdio>

using namespace std;

int main(int argc, char *argv[]) {
    if (argc != 2) {
        cerr << "Usage: " << argv[0] << " <filename>" << endl;
        return 1;
    }

    struct stat s;
    if (stat(argv[1], &s) != 0) {
        perror("stat");
        return 1;
    }

    cout << "Inode: " << s.st_ino << endl;
    cout << "Links: " << s.st_nlink << endl;
    cout << "UID: " << s.st_uid << endl;
    cout << "GID: " << s.st_gid << endl;
    cout << "Size: " << s.st_size << endl;
    cout << "Block size: " << s.st_blksize << endl;
    cout << "Blocks: " << s.st_blocks << endl;

    return 0;
}
