const fs = require('fs');

const output_file = 'readme.md';
const except_list = ['out', '.idea'];

function checkCondition(p) {
    if (!p.isDirectory()) {
        return false;
    }
    if (p.name[0] == '.') {
        return false;
    }
    if (except_list.indexOf(p.name) != -1) {
        return false;
    }
    return true;
}

function makeProblemToggleHtml(alg) {
    let tag = '';
    tag += `
<details>
<summary>${alg}</summary>
<div markdown="1">
    <ul>\n`;

    problemMap[alg].forEach((problem) => {
        if (problem[0] === 'p') {
            const problemNumber = problem.substring(1, problem.indexOf('_'));

            tag += `        <li><a href="http://boj.kr/${problemNumber}">${problem.replace(
                '_',
                ' '
            )}</a></li>\n`;
        } else {
            tag += `        <li>${problem}</li>\n`;
        }
    });
    tag += `    </ul>
</div>
</details>\n`;
    return tag;
}

const result = {};
const studentMap = { HC: 0, YK: 1, MJ: 2, HJ: 3 };
const problemMap = {};

fs.readdirSync('./', { withFileTypes: true }).forEach((p) => {
    const name = p.name;
    const path = './' + name;
    if (!checkCondition(p)) {
        return;
    }
    const student = [0, 0, 0, 0];
    result[name] = student;
    problemMap[name] = [];
    fs.readdirSync(path).forEach((problemDir) => {
        problemMap[name].push(problemDir);
        fs.readdirSync(path + '/' + problemDir).forEach((file) => {
            const ini = file.substring(file.length - 7, file.length - 5);
            ++student[studentMap[ini]];
        });
    });
});

// write md file
if (!fs.existsSync(output_file)) {
    fs.openSync(output_file, 'w', 666);
}
fs.writeFileSync(output_file, '');

// // 백준
const total = [0, 0, 0, 0];
fs.appendFileSync(
    output_file,
    '## Counting\n|    Algorithm    | 김현창 | 양유경 | 정민지 | 정현주 |\n| :-------------: | :----: | :----: | :----: | :----: |\n',
    'utf-8'
);

for (let alg in result) {
    for (let i = 0; i < 4; ++i) total[i] += result[alg][i];
    temp =
        '|' +
        alg +
        '|' +
        result[alg][0] +
        '|' +
        result[alg][1] +
        '|' +
        result[alg][2] +
        '|' +
        result[alg][3] +
        '|\n';
    fs.appendFileSync(output_file, temp, 'utf-8');
}
fs.appendFileSync(
    output_file,
    '| **합계** | **' +
        total[0] +
        '**|**' +
        total[1] +
        '**|**' +
        total[2] +
        '**|**' +
        total[3] +
        '**|\n\n',
    'utf-8'
);

fs.appendFileSync(output_file, '---\n');
fs.appendFileSync(output_file, '## Problems');

for (let alg in problemMap) {
    fs.appendFileSync(output_file, makeProblemToggleHtml(alg));
}

console.log('solved ' + total + '!');
console.log('saved successfully! ' + output_file);
