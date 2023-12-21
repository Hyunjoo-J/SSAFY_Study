const fs = require('fs');

const output_file = 'readme.md';
const except_list = ['out', '.idea'];
const checkMark = '✔';
const uncheckMark = '❌';

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
const result = {};
const studentMap = { HC: 0, YK: 1, MJ: 2, HJ: 3 };
const problemMap = {};

fs.readdirSync('./', { withFileTypes: true }).forEach((p) => {
    const alg = p.name;
    const path = './' + alg;
    if (!checkCondition(p)) {
        return;
    }
    const student = [0, 0, 0, 0];
    result[alg] = student;
    problemMap[alg] = [];
    fs.readdirSync(path).forEach((problemDir) => {
        // problemMap[alg].push(problemDir);
        const checker = [false, false, false, false];
        fs.readdirSync(path + '/' + problemDir).forEach((file) => {
            const ini = file.substring(file.length - 7, file.length - 5);
            ++student[studentMap[ini]];
            checker[studentMap[ini]] = true;
        });
        problemMap[alg][problemDir] = checker;
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
    '## Algorithm\n|    Algorithm    | 김현창 | 양유경 | 정민지 | 정현주 |\n| :-------------: | :----: | :----: | :----: | :----: |\n',
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

function makeProblemTableHtml(alg) {
    let tag = '';
    tag += '\n### ' + alg;
    tag +=
        '\n|    문제    |    제목    | 김현창 | 양유경 | 정민지 | 정현주 |\n| :-------------: | :----: | :----: | :----: | :----: | :----: |';
    for (const problem in problemMap[alg]) {
        const splited = problem.split('_');

        if (splited[0].charAt(0) === 'p') {
            const probNumber = splited[0].substring(1);
            tag += `\n|    <a href="http://boj.kr/${probNumber}">${probNumber}    | `;
            tag += `    <a href="${alg}/${problem}">${splited[1]}    | `;
        } else {
            tag += `\n|    ${splited[0]}    | `;
            tag += `    ${splited[1]}    | `;
        }
        for (let i = 0; i < 4; ++i) {
            tag += `${problemMap[alg][problem][i] ? checkMark : uncheckMark} |`;
        }
    }
    //     tag += `
    // <details>
    // <summary>${alg}</summary>
    // <div markdown="1">
    //     <ul>\n`;

    //     problemMap[alg].forEach((problem) => {
    //         tag += `        <li><a href="${alg}/${problem}">${problem.replace(
    //             '_',
    //             ' '
    //         )}</a></li>\n`;
    //     });
    //     tag += `    </ul>
    // </div>
    // </details>\n`;
    return tag;
}

for (let alg in problemMap) {
    fs.appendFileSync(output_file, makeProblemTableHtml(alg));
}

console.log('solved ' + total + '!');
console.log('saved successfully! ' + output_file);
