import { SerializedStyles } from "@emotion/react";
import React from "react";
import WrapStyle from "./WrapStyle";

function MainWrapper(props: { children: React.ReactNode; style?: SerializedStyles }) {
    return (
        <main
            css={[WrapStyle, props?.style]}
        >
            {props.children}
        </main>
    );
}

export default MainWrapper;