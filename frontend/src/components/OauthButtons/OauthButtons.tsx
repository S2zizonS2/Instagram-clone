import React from "react";
import Facebook from "./Facebook";
import BaseProps from "@/Type/BaseProps";

function OauthButtons(props: BaseProps) {
    return (
        <section
            className={props?.className}
        >
            <Facebook />
        </section>
    );
}

export default OauthButtons;