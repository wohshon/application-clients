"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.isValidType = exports.asData = exports.isJsonContentType = exports.clone = exports.asBase64 = exports.asBuffer = exports.isBuffer = exports.isBase64 = exports.equalsOrThrow = exports.isStringOrObjectOrThrow = exports.isDefinedOrThrow = exports.isStringOrThrow = exports.isBinary = exports.isDate = exports.isInteger = exports.isBoolean = exports.isDefined = exports.isObject = exports.isString = exports.ValidationError = void 0;
/**
 * An Error class that will be thrown when a CloudEvent
 * cannot be properly validated against a specification.
 */
class ValidationError extends TypeError {
    constructor(message, errors) {
        const messageString = errors instanceof Array
            ? // eslint-disable-next-line @typescript-eslint/ban-ts-comment
             
            // @ts-ignore
            errors === null || 
            // @ts-ignore
            errors === void 0 ? void 0 : 
            // @ts-ignore
            errors.reduce((accum, err) => accum.concat(`
  ${err instanceof Object ? JSON.stringify(err) : err}`), message) : message;
        super(messageString);
        this.errors = errors ? errors : [];
    }
}
exports.ValidationError = ValidationError;
exports.isString = (v) => typeof v === "string";
exports.isObject = (v) => typeof v === "object";
exports.isDefined = (v) => v && typeof v !== "undefined";
exports.isBoolean = (v) => typeof v === "boolean";
exports.isInteger = (v) => Number.isInteger(v);
exports.isDate = (v) => v instanceof Date;
exports.isBinary = (v) => v instanceof Uint32Array;
exports.isStringOrThrow = (v, t) => exports.isString(v)
    ? true
    : (() => {
        throw t;
    })();
exports.isDefinedOrThrow = (v, t) => exports.isDefined(v)
    ? true
    : (() => {
        throw t;
    })();
exports.isStringOrObjectOrThrow = (v, t) => exports.isString(v)
    ? true
    : exports.isObject(v)
        ? true
        : (() => {
            throw t;
        })();
exports.equalsOrThrow = (v1, v2, t) => v1 === v2
    ? true
    : (() => {
        throw t;
    })();
exports.isBase64 = (value) => Buffer.from(value, "base64").toString("base64") === value;
exports.isBuffer = (value) => value instanceof Buffer;
exports.asBuffer = (value) => exports.isBinary(value)
    ? Buffer.from(value)
    : exports.isBuffer(value)
        ? value
        : (() => {
            throw new TypeError("is not buffer or a valid binary");
        })();
exports.asBase64 = (value) => exports.asBuffer(value).toString("base64");
exports.clone = (o) => JSON.parse(JSON.stringify(o));
exports.isJsonContentType = (contentType) => contentType && contentType.match(/(json)/i);
exports.asData = (data, contentType) => {
    // pattern matching alike
    const maybeJson = exports.isString(data) && !exports.isBase64(data) && exports.isJsonContentType(contentType) ? JSON.parse(data) : data;
    return exports.isBinary(maybeJson) ? exports.asBase64(maybeJson) : maybeJson;
};
exports.isValidType = (v) => exports.isBoolean(v) || exports.isInteger(v) || exports.isString(v) || exports.isDate(v) || exports.isBinary(v) || exports.isObject(v);
