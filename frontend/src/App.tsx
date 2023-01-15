import React from "react";

import GlobalStyle from "./GlobalStyle";
import { Route, Routes } from "react-router-dom";

import Accounts from "./pages/Accounts/index";
import Layout from "./components/Layout/Layout";
import { QueryClient, QueryClientProvider } from "react-query";

const queryClient = new QueryClient({
    defaultOptions: {
        queries: {
            suspense: true,
            useErrorBoundary: true,
            retry: 0,
            refetchOnWindowFocus: false,

        },
    },
});

function App() {
    return (
        <React.Fragment>
            <QueryClientProvider client={queryClient}>
                <GlobalStyle />
                <Routes>
                    <Route path="/accounts/*" element={<Accounts />} />
                    <Route path="*" element={<Layout />} />
                </Routes>
            </QueryClientProvider>
        </React.Fragment>
    );
}


export default App;