requirejs.config({
    baseUrl: "resources",
    waitSeconds: 200,
    paths: {
        "jquery": "js/jquery/jquery-3.3.1.min",
        "domready": "common/js/require/domReady",
        "Promise": "https://cdn.jsdelivr.net/npm/bluebird@3.7.1/js/browser/bluebird.min",   // IE Promise 사용가능토록

        "scrap": "js/scrap/scrap",
    },
    //waitSeconds: 200,
    shim: {
        "jquery": {"exports": "$"},
        "scrap": {"exports": "scrap", "deps": ["jquery"]},
    }
});
