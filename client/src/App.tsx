import * as React from "react";
import { Admin, CustomRoutes, Resource } from "react-admin";
import polyglotI18nProvider from "ra-i18n-polyglot";
import { Route } from "react-router";

import authProvider from "./authProvider";
import { Login, Layout } from "./layout";
import { Dashboard } from "./dashboard";
import englishMessages from "./i18n/en";
import { lightTheme } from "./layout/themes";

import visitors from "./visitors";
import orders from "./orders";
import products from "./products";
import invoices from "./invoices";
import categories from "./categories";
import reviews from "./reviews";
import dataProviderFactory from "./dataProvider";
import Configuration from "./configuration/Configuration";
import Segments from "./segments/Segments";
import simpleRestProvider from "ra-data-json-server";
import jsonServerProvider from "ra-data-json-server";
import { me, myAuth } from "./myAuth";

const i18nProvider = polyglotI18nProvider((locale) => {
  if (locale === "fr") {
    return import("./i18n/fr").then((messages) => messages.default);
  }

  // Always fallback on english
  return englishMessages;
}, "en");

//  Official data prvider config
const dataProvider = dataProviderFactory(
  process.env.REACT_APP_DATA_PROVIDER || ""
);

const myData = simpleRestProvider("http://localhost:3060");

const App = () => {
  React.useEffect(() => {
    me();
  }, []);
  return (
    <Admin
      title=""
      // dataProvider={dataProvider}
      // authProvider={authProvider}
      dataProvider={myData}
      authProvider={myAuth}
      dashboard={Dashboard}
      loginPage={Login}
      layout={Layout}
      i18nProvider={i18nProvider}
      disableTelemetry
      theme={lightTheme}
    >
      <CustomRoutes>
        <Route path="/configuration" element={<Configuration />} />
        <Route path="/segments" element={<Segments />} />
      </CustomRoutes>
      <Resource name="customers" {...visitors} />
      <Resource name="commands" {...orders} options={{ label: "Orders" }} />
      <Resource name="invoices" {...invoices} />
      <Resource name="products" {...products} />
      <Resource name="categories" {...categories} />
      <Resource name="reviews" {...reviews} />
    </Admin>
  );
};

export default App;
