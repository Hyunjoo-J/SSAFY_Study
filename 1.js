const fs = require('fs');

const output_file = 'readme.md';
const except_list = ['out', '.idea'];

function check_condition(p) {
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

fs.readdirSync('./', { withFileTypes: true }).forEach((p) => {
    const name = p.name;
    const path = './' + name;
    if (!check_condition(p)) {
        return;
    }
    result[name] = fs.readdirSync(path).length;
});

// write md file
if (!fs.existsSync(output_file)) {
    fs.openSync(output_file, 'w', 666);
}
fs.writeFileSync(output_file, '');

// // 백준
let total = 0;
fs.appendFileSync(
    output_file,
    '## Algorithm\n|    Algorithm    | solved |\n| :-------------: | :----: |\n',
    'utf-8'
);

for (let key in result) {
    total += result[key];
    temp = '|' + key + '|' + result[key] + '|\n';
    fs.appendFileSync(output_file, temp, 'utf-8');
}
fs.appendFileSync(output_file, '| **sum** | **' + total + '**|\n\n', 'utf-8');
console.log('solved ' + total + '!');

console.log('saved successfully! ' + output_file);
