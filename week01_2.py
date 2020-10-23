import sys
line = sys.stdin.readline().rstrip()
num = int(sys.stdin.readline().rstrip())
line = list(line)
pointer = len(line)

if pointer <= 100000 and 1 <= num and num <= 500000:
    for i in range(0, num):
        cmd = sys.stdin.readline().rstrip()
        if cmd[0] == 'L':
            if pointer != 0:
                pointer = pointer - 1
        elif cmd[0] == 'D':
            if pointer != len(line):
                pointer = pointer + 1
        elif cmd[0] == 'P':
            line.insert(pointer, cmd[2])
            pointer = pointer + 1
        elif cmd[0] == 'B':
            if pointer != 0:
                del line[pointer-1]
                pointer = pointer - 1
print(''.join(line))