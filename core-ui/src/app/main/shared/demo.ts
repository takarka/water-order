export interface Demo {
    firstName: string;
    lastName: string;
    age: number;
}
// Module path dynamic loading ...
export const demo_module_path = 'app/main/demo/demo.module#DemoModule';
export const country_module_path = 'app/main/countries/country.module#CountryModule';
