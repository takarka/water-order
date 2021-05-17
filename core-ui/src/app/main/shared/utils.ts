export function enumValues<T>(t: T) {
    return Object.keys(t).filter(type => isNaN(<any>type) && type !== 'values');
}

export function enumType(val, et) {
    let type = '';
    for (const key in et) {
        if (val === et[key]) {
            type = key;
            break;
        }
    }
    return type;
}
