import { Page } from "@/components/layout/page";
import { TypographyH1 } from "@/components/typography/typography-h1";
import { Button } from "@/components/ui/button";
import Link from "next/link";

export default function ExamplePage() {
  return (
    <Page>
      <TypographyH1>Examples</TypographyH1>
      <ul>
        <li>
          <Link href="/examples/greeting">
            <Button variant="link">/examples/greeting</Button>
          </Link>
        </li>
        <li>
          <Link href="/examples/hello">
            <Button variant="link">/examples/hello</Button>
          </Link>
        </li>
        <li>
          <Link href="/examples/shadcn">
            <Button variant="link">/examples/shadcn</Button>
          </Link>
        </li>
      </ul>
    </Page>
  );
}
